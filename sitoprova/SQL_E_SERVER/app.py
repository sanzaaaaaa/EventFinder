
# Parte web client 

from flask import Flask, render_template, request, redirect, url_for, session, flash
from flask_mysqldb import MySQL
from werkzeug.security import generate_password_hash, check_password_hash

app = Flask(__name__)
app.secret_key = 'tuo_super_segreto'  # Cambia questa chiave!

# Configurazione per MySQL
app.config['MYSQL_HOST'] = 'localhost'
app.config['MYSQL_USER'] = 'root'
app.config['MYSQL_PASSWORD'] = '1234'
app.config['MYSQL_DB'] = 'eventfinder'

mysql = MySQL(app)

# Home page
@app.route('/')
def home():
    if 'loggedin' in session:
        return render_template('index.html', username=session['username'])
    return redirect(url_for('login'))

# Login
@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        email = request.form['email']
        password = request.form['password']

        cur = mysql.connection.cursor()
        cur.execute("SELECT * FROM utenti WHERE email = %s", (email,))
        user = cur.fetchone()
        cur.close()

        if user and check_password_hash(user[5], password):  # password al 6° campo
            session['loggedin'] = True
            session['id'] = user[0]
            session['username'] = user[1]  # nome
            return redirect(url_for('home'))
        else:
            flash('Email o password errati', 'danger')

    return render_template('login.html')

# Registrazione
@app.route('/register', methods=['GET', 'POST'])
def register():
    if request.method == 'POST':
        nome = request.form['nome']
        cognome = request.form['cognome']
        email = request.form['email']
        data_di_nascita = request.form['data_di_nascita']
        password = request.form['password']

        hashed_pw = generate_password_hash(password)
        cur = mysql.connection.cursor()
        cur.execute("SELECT * FROM utenti WHERE email = %s", (email,))
        existing = cur.fetchone()
        if existing:
            flash('Email già registrata', 'warning')
        else:
            cur.execute("INSERT INTO utenti (nome, cognome, email, data_di_nascita, password) VALUES (%s, %s, %s, %s, %s)",
                        (nome, cognome, email, data_di_nascita, hashed_pw))
            mysql.connection.commit()
            flash('Registrazione completata! Ora puoi accedere.', 'success')
        cur.close()
    return render_template('register.html')

# Logout
@app.route('/logout')
def logout():
    session.clear()
    return redirect(url_for('login'))

# Eventi (protetta)
@app.route('/eventi')
def eventi():
    if 'loggedin' not in session:
        return redirect(url_for('login'))
    cur = mysql.connection.cursor()
    cur.execute("SELECT * FROM eventi")
    eventi = cur.fetchall()
    cur.close()
    return render_template('eventi.html', eventi=eventi)

# Contatti
@app.route('/contatti')
def contatti():
    return render_template('contatti.html')

# Dashboard (protetta)
@app.route('/dashboard')
def dashboard():
    if 'loggedin' not in session:
        return redirect(url_for('login'))
    return render_template('dashboard.html')

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
    app.run(debug=True)
    
# !!Parte applicazione!!



from flask import Flask, request, jsonify
import pymysql
import pymysql.cursors

app = Flask(__name__)

@app.route('/')
def home():
    query = "SELECT * FROM utenti"  
    with connection.cursor() as cursor:
        cursor.execute(query)
        lista = cursor.fetchall()
    
   
    for utente in lista:
        if utente['data_di_nascita']:
            utente['data_di_nascita'] = utente['data_di_nascita'].strftime('%Y/%m/%d')
    
    return jsonify(lista)

@app.route('/register', methods=['POST'])
def register():
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


@app.route('/login', methods=['POST'])
def login():
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




if __name__ == '__main__':
    app.run(host="0.0.0.0", debug=True)



