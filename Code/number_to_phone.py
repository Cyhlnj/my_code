for i in range(10000):
    num = str(i+15809910000)
    ID = str(i+9291875765)
    with open('10000.text', 'a', encoding='utf-8') as f:
        f.write('BEGIN:VCARD\n' + 'FN:' + num + '\nTEL;type=CELL;type=VOICE;type=pref:' + num
                + '\nCID:' + ID + '\nUID:' + ID + '\nVERSION:3.0\nEND:VCARD\n')
