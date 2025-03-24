from flask import Flask, request, jsonify
import pymysql
import pymysql.cursors
import bcrypt

app = Flask(__name__)


connection = pymysql.connect(
    host='localhost',
    user='root',
    password='1234',
    database='mydb',
    autocommit=True,
    cursorclass=pymysql.cursors.DictCursor
)

@app.route('/')
def home():
    query = "SELECT * FROM Utenti"
    with connection.cursor() as cursor:
        cursor.execute(query)
        lista = cursor.fetchall()
    for utente in lista:
        if utente['data_di_nascita']:
            utente['data_di_nascita'] = utente['data_di_nascita'].strftime('%Y-%m-%d')
        
    
    return jsonify(lista)

@app.route('/register', methods=['POST'])
def register():
    data = request.get_json()

    nome = data.get('nome')
    cognome = data.get('cognome')
    email = data.get('email')
    data_di_nascita = data.get('data_di_nascita')
    password_hash = data.get('password_hash')

    check_query = "SELECT * FROM Utenti WHERE email = %s"
    with connection.cursor() as cursor:
        cursor.execute(check_query, (email,))
        existing_user = cursor.fetchone()
        if existing_user:
            return jsonify({'message': 'Email già registrata'}), 400
        
        password_hash = bcrypt.hashpw(password_plain.encode('utf-8'), bcrypt.gensalt())


    query = """
        INSERT INTO Utenti (nome, cognome, email, data_di_nascita, password_hash) 
        VALUES (%s, %s, %s, %s, %s)
    """
    try:
        with connection.cursor() as cursor:
            cursor.execute(query, (nome, cognome, email, data_di_nascita, password_hash))
        return jsonify({'message': 'Registrazione completata con successo!'}), 201
    except pymysql.MySQLError as e:
        return jsonify({'message': f'Errore durante la registrazione: {str(e)}'}), 500

if __name__ == '__main__':
    app.run(debug=True)


@app.route('/login', methods=['POST'])
def login():
    data = request.get_json()
    
    email = data.get('email')
    password_plain = data.get('password_hash')  # Password inserita dall'utente

    query = "SELECT * FROM Utenti WHERE email = %s"
    with connection.cursor() as cursor:
        cursor.execute(query, (email,))
        user = cursor.fetchone()

        if user:
            stored_password = user['password_hash'].encode('utf-8')
            if bcrypt.checkpw(password_plain.encode('utf-8'), stored_password):
                return jsonify({'message': 'Login effettuato con successo!'}), 200
            else:
                return jsonify({'message': 'Password errata'}), 401
        else:
            return jsonify({'message': 'Email non trovata'}), 404