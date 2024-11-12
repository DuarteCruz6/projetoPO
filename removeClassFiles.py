import os

path = r"/Users/duartecruz/Desktop/FACULDADE/2_ano/PO/proj/code/entrega"

# Percorrer todas as pastas e subpastas
for root, dirs, files in os.walk(path):
    for file in files:
        if file.endswith(".class"):  # Verifica se o ficheiro termina com .class
            os.remove(os.path.join(root, file))  # Remove o ficheiro
            print("Removed:", file)  # Imprime o nome do ficheiro removido
