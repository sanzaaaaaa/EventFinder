from flask import Flask, render_template, request, redirect, url_for
from flask_mysqldb import MySQL

app = Flask(__name__)

# Configurazione per Flask e MySQL
app.config['MYSQL_HOST'] = 'localhost'  # O l'IP se è su un server remoto
app.config['MYSQL_USER'] = 'root'  # Il tuo nome utente MySQL
app.config['MYSQL_PASSWORD'] = '1234'  # La tua password MySQL
app.config['MYSQL_DB'] = 'eventfinder'  # Il nome del tuo database

mysql = MySQL(app)

@app.route('/')
def home():
    return render_template('index.html')

@app.route('/eventi')
def eventi():
    # Recupera gli eventi dal database
    cur = mysql.connection.cursor()
    cur.execute("SELECT * FROM eventi")
    eventi = cur.fetchall()
    return render_template('eventi.html', eventi=eventi)

@app.route('/contatti')
def contatti():
    return render_template('contatti.html')

@app.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        email = request.form['email']
        password = request.form['password']
        
        # Connessione al database per verificare l'utente
        cur = mysql.connection.cursor()
        cur.execute("SELECT * FROM utenti WHERE email = %s AND password = %s", (email, password))
        user = cur.fetchone()
        
        if user:
            return redirect(url_for('home'))
        else:
            return 'Credenziali errate', 401  # Puoi personalizzare l'errore
        
    return render_template('login.html')

@app.route('/register', methods=['GET', 'POST'])
def register():
    if request.method == 'POST':
        nome = request.form['nome']
        cognome = request.form['cognome']
        email = request.form['email']
        data_di_nascita = request.form['data_di_nascita']
        password = request.form['password']
        
        # Connessione al database per inserire l'utente
        cur = mysql.connection.cursor()
        cur.execute("INSERT INTO utenti (nome, cognome, email, data_di_nascita, password) VALUES (%s, %s, %s, %s, %s)",
                    (nome, cognome, email, data_di_nascita, password))
        mysql.connection.commit()
        
        return redirect(url_for('login'))
    
    return render_template('register.html')

if __name__ == "__main__":
    app.run(debug=True)

