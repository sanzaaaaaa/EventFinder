from flask import Flask, flash, jsonify, render_template, request, url_for, redirect, session
import pymysql
import pymysql.cursors



app = Flask(__name__)
app.config.update(SECRET_KEY='1234')


# Configurazione per MySQL
connection = pymysql.connect(
host='localhost',
user='root',
password='1234',
database='eventfinder',
autocommit=True,
cursorclass=pymysql.cursors.DictCursor
)



# Home page
@app.route('/')
def home():
    return render_template('index.html')

# Login
@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        data = request.form
        email = data.get('email') #email = data.get('email')
        password = data.get('password')

        cursor = connection.cursor()
        query = "select * from utenti where email = %s and password = %s"
        cursor.execute(query, (email, password))
        user = cursor.fetchone()
        cursor.close()

        if user:
            session["username"]=user["id"]
            session["nome"]=user["nome"]
            session['logged'] = True
            return redirect('/')
        else:
            return "utente non trovato"
    else:
        return render_template('login.html')
        

        

        """if user and password(user[5], password):  # password al 6° campo
            session['loggedin'] = True
            session['id'] = user[0]
            session['username'] = user[1]  # nome
            return redirect(url_for('home'))
        else:
            flash('Email o password errati', 'danger') """

    

# Registrazione
@app.route('/register', methods=['GET', 'POST'])
def register():
    if request.method == 'POST':
        data = request.form
        nome = data.get('nome')
        cognome = data.get('cognome')
        email = data.get('email')
        data_di_nascita = data.get('data_di_nascita')
        password = data.get('password')


        with connection.cursor() as cursor:
            query = "INSERT INTO utenti (nome, cognome, email, data_di_nascita, password) VALUES (%s, %s, %s, %s, %s)"
            cursor.execute(query, (nome, cognome, email, data_di_nascita, password))
            cursor = cursor.fetchone()
        return redirect (url_for('home'))
    return render_template('register.html')

# Logout
@app.route('/logout')
def logout():
    session.clear()
    return redirect(url_for('login'))

# Eventi
@app.route('/eventi')
def eventi():
    if 'logged' not in session:
        return redirect(url_for('login'))
    else:
        with connection.cursor() as cursor:
                cursor.execute("SELECT * FROM eventi")
        eventi = cursor.fetchall()
        return render_template('eventi.html', eventi=eventi)

# Contatti
@app.route('/contatti')
def contatti():
    return render_template('contatti.html')

# Dashboard (protetta)
@app.route('/dashboard')
def dashboard():
    if 'logged' not in session:
        return redirect(url_for('login'))
    return render_template('dashboard.html')

@app.route('/aggiungievento', methods=['GET', 'POST'])
def aggiungievento():

    if request.method == 'POST':
        data = request.form
        immagine = data.get('immagine')
        titolo = data.get('titolo')
        luogo = data.get('luogo')
        data_evento = data.get('data')
        info_evento = data.get('info_evento')
        artista = data.get('artista')
        prezzo = data.get('prezzo')

        with connection.cursor() as cursor:
            query = "INSERT INTO eventi (urlimage, titolo, luogo, data_evento, info_evento, info_artista, prezzo) VALUES (%s, %s, %s, %s, %s, %s, %s)"
            cursor.execute(query, (immagine, titolo, luogo, data_evento, info_evento, artista, prezzo))
            cursor = cursor.fetchone()
        return redirect (url_for('eventi'))

    return render_template('aggiungievento.html')

@app.route('/info_evento', methods=['GET', 'POST'])
def info_evento():
    return render_template('infoevento.html')


"""@app.route('/aggiungi_preferiti', methods=['POST'])
def aggiungi_preferiti():
    data = request.json
    utente_id = data.get('utente_id')
    evento_id = data.get('evento_id')
    
    
    conn = get_connection()
    cursor = conn.cursor()
    query = "INSERT INTO preferiti (utente_id, evento_id) VALUES(%s, %s)"
    try:
        cursor.execute(query, (utente_id, evento_id))
        conn.commit()
        return jsonify({"status": "success"})
    except Exception as e:
        return jsonify({"status": "error", "message": str(e)})
    finally:
        cursor.close()
        conn.close() """

# Avvio
if __name__ == '__main__':
    app.run(host= '0.0.0.0', debug=True)
