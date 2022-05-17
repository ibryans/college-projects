import os
import PySimpleGUI as sg
from matplotlib import pyplot as plt;
import matplotlib.image as mpimg
import control
import tkinter.filedialog as tkf

from opencv import opencv

def open_image(filename):
    if os.path.exists(filename):
        opencv.abrir_imagem(filename)
        # image = Image.open(filename)
        # image.thumbnail((400,400))
        # bio = io.BytesIO()
        # image.save(bio, format="PNG")
        # tela(bio.getvalue())

def tela():

    menu_def = [
        ['&Arquivo', ['&Abrir imagem', '&Sair']],
        ['&Imagem', ['&Limpar', '&Selecionar região', '&Exibir características da imagem/região']],
        ['&Classificador', ['&Ler imagens de treino', '&Treinar classificador', '&Classificar imagem/região']] 
    ]

    layout = [
        [sg.MenuBar(menu_def)],
        # [sg.Image(imagePath)],
        [sg.Column(layout='')],[sg.Column(layout='')],[sg.Column(layout='')],
        [sg.Text('Saída')],
        [sg.Output(size=(54, 5))],
    ]
    
    window = sg.Window('Imagem selecionada', layout)
    
    while True:
        event, values = window.read()


        if event in (None, 'Sair'):
            break

        elif event == 'Abrir imagem':
            filename = os.path.abspath(tkf.askopenfilename(initialdir = os.getcwd(), title="Selecione sua imagem"))
            # window.close()
            open_image(filename)

        elif event == 'Limpar':
            opencv.reset_image()

        elif event == 'Selecionar região':
            print('\n****** Selecionando Região ******')
            control.mark_image_rectangle = True

        elif event == 'Exibir características da imagem/região':
            print("\n****** Características da imagem ******")

        elif event == 'Ler imagens de treino':
            print("\n****** Lendo imagens de treino ******")

        elif event == 'Treinar classificador':
            print("\n****** Treinando classificador ******")

        elif event == 'Classificar imagem/região':
            print("\n****** Classificando imagem ******")