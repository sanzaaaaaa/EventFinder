from flask import Flask, jsonify, render_template, request, url_for, redirect, session
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


# Profilo
#..............................
@app.route('/profilo')
def profilo():
    if 'logged' not in session:
        return redirect(url_for('login'))

    user_id = session['username']

    with connection.cursor() as cursor:
        query = "SELECT nome, cognome, email, data_di_nascita FROM utenti WHERE id = %s"
        cursor.execute(query, (user_id,))
        utente = cursor.fetchone()

    if utente:
        
        if utente['data_di_nascita']:
            utente['data_di_nascita'] = utente['data_di_nascita'].strftime('%d %B %Y')

       
        return render_template('profiloutente.html', utente=utente)
    else:
        return "Utente non trovato", 404


# Aggiungi evento
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

# Elimina evento
@app.route('/elimina_evento/<int:evento_id>', methods=['POST'])
def elimina_evento(evento_id):

    with connection.cursor() as cursor:
        
        query = "DELETE FROM eventi WHERE id = %s"
        cursor.execute(query, (evento_id,))
        connection.commit()  

    return redirect(url_for('eventi'))




# backend applicazione mobile

@app.route('/')
def mobile_home():
    query = "SELECT * FROM utenti"  
    with connection.cursor() as cursor:
        cursor.execute(query)
        lista = cursor.fetchall()
    
   
    for utente in lista:
        if utente['data_di_nascita']:
            utente['data_di_nascita'] = utente['data_di_nascita'].strftime('%Y/%m/%d')
    
    return jsonify(lista)

@app.route('/mobile_register', methods=['POST'])
def mobile_register():
    data = request.get_json()
    nome = data.get('nome')
    cognome = data.get('cognome')
    email = data.get('email')
    data_di_nascita = data.get('data_di_nascita')
    password = data.get('password') 

   
    cursor = connection.cursor()
    query = "INSERT INTO utenti (nome, cognome, email, data_di_nascita, password) VALUES (%s, %s, %s, %s, %s)"
    cursor.execute(query, (nome, cognome, email, data_di_nascita, password))
    connection.commit()
    cursor.close()
    return jsonify({"message": "Utente registrato correttamente!"}), 201


@app.route('/mobile_login', methods=['POST'])
def mobile_login():
    data = request.get_json()

    email = data.get('email')
    password_plain = data.get('password') 

   
    query =  "SELECT nome, cognome, email, data_di_nascita, password FROM utenti WHERE email = %s"
    with connection.cursor() as cursor:
        cursor.execute(query, (email,))
        user = cursor.fetchone()

        if user:
       
            if password_plain == user['password']:
           
                data_nascita = user['data_di_nascita'].strftime('%d-%m-%Y') 

               
                return jsonify({
                    'nome': user['nome'],
                    'cognome': user['cognome'],
                    'email': user['email'],
                    'data_di_nascita': data_nascita
                }), 200
            else:
                return jsonify({'message': 'Password errata'}), 401
        else:
            return jsonify({'message': 'Email non trovata'}), 404



@app.route('/get_users', methods=['GET'])
def get_users():
    query = "SELECT nome, cognome FROM utenti" 
    with connection.cursor() as cursor:
        cursor.execute(query)
        users = cursor.fetchall() 

    return jsonify(users) 

@app.route('/get_eventi', methods=['GET'])
def get_eventi():
    query = "SELECT * FROM eventi" 
    with connection.cursor() as cursor:
        cursor.execute(query)
        eventi = cursor.fetchall() 

    return jsonify(eventi) 

# Aggiungi evento ai preferiti
@app.route('/aggiungi_preferiti', methods=['POST'])
def aggiungi_preferiti():

    connection = pymysql.connect(
    host="localhost",
    user="root",
    password="1234",
    database="eventfinder",
    autocommit=True,
    cursorclass=pymysql.cursors.DictCursor
    )

    data = request.json
    utente_id = data.get('utente_id')
    evento_id = data.get('evento_id')

    print("Ricevuto utente_id:", utente_id)
    print("Ricevuto evento_id:", evento_id)
    
    
    cursor = connection.cursor()
    query = "INSERT INTO preferiti (utente_id, evento_id) VALUES(%s, %s)"
    try:
        cursor.execute(query, (utente_id, evento_id))
        connection.commit()
        return jsonify({"status": "success"})
    except Exception as e:
        return jsonify({"status": "error", "message": str(e)})
    finally:
        cursor.close()
        connection.close()

@app.route("/get_preferiti/<int:utente_id>")
def get_preferiti(utente_id):
    

    if utente_id is None:
        return jsonify({"error": "utente_id mancante"}), 400

    query = "SELECT eventi.* FROM eventi JOIN preferiti ON eventi.id = preferiti.evento_id WHERE preferiti.utente_id = %s"
    with connection.cursor() as cursor:
        cursor.execute(query, (utente_id,))
        preferiti = cursor.fetchall() 

    return jsonify(preferiti) 


# Avvio
if __name__ == '__main__':
    app.run(host= '0.0.0.0', debug=True)
