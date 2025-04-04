from flask import Flask, request, jsonify
import pymysql
import pymysql.cursors

# Connessione al database MySQL
connection = pymysql.connect(
    host='localhost',
    user='root',
    password='1234',  # La password per il tuo MySQL
    database='eventfinder',
    cursorclass=pymysql.cursors.DictCursor,
    autocommit=True,
)

app = Flask(__name__)

@app.route('/')
def home():
    query = "SELECT * FROM utenti"  # Usa la tabella 'Utenti' corretta
    with connection.cursor() as cursor:
        cursor.execute(query)
        lista = cursor.fetchall()
    
    # Formatta la data di nascita se presente
    for utente in lista:
        if utente['data_di_nascita']:
            utente['data_di_nascita'] = utente['data_di_nascita'].strftime('%Y/%m/%d')
    
    return jsonify(lista)

@app.route('/register', methods=['POST'])
def register():
    data = request.get_json()

    # Estrai i dati dal corpo della richiesta
    nome = data.get('nome')
    cognome = data.get('cognome')
    email = data.get('email')
    data_di_nascita = data.get('data_di_nascita')
    password = data.get('password')  # La password è in chiaro (o può essere hashata)

    # Inserisci i dati nel database
    cursor = connection.cursor()
    query = "INSERT INTO utenti (nome, cognome, email, data_di_nascita, password) VALUES (%s, %s, %s, %s, %s)"
    cursor.execute(query, (nome, cognome, email, data_di_nascita, password))
    connection.commit()
    cursor.close()
    return jsonify({"message": "Utente registrato correttamente!"}), 201

@app.route('/login', methods=['POST'])
def login():
    data = request.get_json()

    # Estrai email e password dal corpo della richiesta
    email = data.get('email')
    password_plain = data.get('password')  # La password inserita dall'utente

    # Verifica l'utente nel database
    query = "SELECT * FROM utenti WHERE email = %s"
    with connection.cursor() as cursor:
        cursor.execute(query, (email,))
        user = cursor.fetchone()

        if user:
            # Confronta la password in chiaro
            stored_password = user['password']
            if password_plain == stored_password:
                return jsonify({'message': 'Login effettuato con successo!'}), 200
            else:
                return jsonify({'message': 'Password errata'}), 401
        else:
            return jsonify({'message': 'Email non trovata'}), 404
        
        
@app.route('/get_users', methods=['GET'])
def get_users():
    query = "SELECT nome, cognome FROM utenti"  # Seleziona solo i campi necessari
    with connection.cursor() as cursor:
        cursor.execute(query)
        users = cursor.fetchall()  # Ottiene tutti gli utenti

    return jsonify(users)  # Flask converte direttamente la lista di dizionari in JSON

@app.route('/aggiungi_preferiti', methods=['POST'])
def aggiungi_preferiti():
    data = request.json
    utente_id = data.get('utente_id')
    evento_id = data.get('evento_id')

    conn = get_connection()
    cursor = conn.cursor()
    query = "INSERT INTO preferiti (utente_id, evento_id) VALUES(%s, %s)"
    try:
        cursor.execute(query, (id_utente, id_evento))
        conn.commit()
        return jsonify({"status": "success"})
    except Exception as e:
        return jsonify({"status": "error", "message": str(e)})
    finally:
        cursor.close()
        conn.close()


if __name__ == '__main__':
    app.run(host="0.0.0.0", debug=True)