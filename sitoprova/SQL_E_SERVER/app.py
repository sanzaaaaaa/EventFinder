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

# Avvio
if __name__ == '__main__':
    app.run(debug=True)
