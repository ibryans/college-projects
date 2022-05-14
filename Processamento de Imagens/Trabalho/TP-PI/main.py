from asyncio.windows_events import NULL
import PySimpleGUI as sg
from matplotlib import pyplot as plt;
import matplotlib.image as mpimg
import parameters

elements_col_size = (60, 0)
zoom_buttons_size = (20, 0)

menu_def = [['&Opções', ['&Sair']],
                ['&Ajuda', ['&Sobre']] ]

layout = [
    [sg.MenuBar(menu_def)],
    [sg.Text('Processamento de Imagens - Trabalho Prático', size=(40, 1), justification='center', font=("Helvetica", 16), relief=sg.RELIEF_RIDGE, k='-TEXT HEADING-', enable_events=True)],
    [sg.Text('Reconhecimento de padrões por textura em imagens mamográficas', size=(60, 1), justification='center', font=("Helvetica", 10), relief=sg.RELIEF_RIDGE, k='-TEXT HEADING-', enable_events=True)],
    [sg.Column(layout='')],[sg.Column(layout='')],[sg.Column(layout='')],
    [sg.FileBrowse('Selecionar imagem', size=elements_col_size, key="select_image", enable_events=True, button_color=parameters.color_button_notselected)],
    [sg.Button('Teste 1', size=elements_col_size, enable_events=True,)],
    [sg.Button('Teste 2', size=elements_col_size, enable_events=True,)],
    [sg.Button('Teste 3', size=elements_col_size, enable_events=True,)],
    [sg.Button('Teste 4', size=elements_col_size, enable_events=True,)],
]
  
window = sg.Window('Aplicação', layout)
  
while True:
    event, values = window.read()
      
    if event == 'Display':
        # Update the "output" text element
        # to be the value of "input" element
        window['-OUTPUT-'].update(values['-IN-'])

    if event == 'select_image':
            imgPath = values['select_image']
            img = mpimg.imread(imgPath)
            plt.imshow(img)
            plt.show()
            # imgPath = values[event]

            # if imgPath != "": 
            #     control.image_cropped = False
            #     control.image_checked = False
            #     opencv.abrir_imagem(imgPath)

    if event in (None, 'Sair'):
        break
  
window.close()