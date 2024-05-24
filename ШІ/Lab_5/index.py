
import random
import numpy as np
def MakeCurriculumForClass(clas, n):     #generate a curriculum for each class.
    curriculum = []
    i = n
    while i < len(data):
        j = 0
        while j < int(data[i][1]):
            curriculum.append([clas, data[i][0], data[i][2], data[i][3]])
            j += 1
        i += 1    
    for i in curriculum:
        if i[2] == '0':
            for j in data:
                if j[0] == clas:
                    i[2] = j[1]
                    break
        if i[3] == '0':
            i[3] = i[0]
    return random.sample(curriculum, len(curriculum))

def CountCoincidence(curriculum, n):     # Counts the coincidences or conflicts in the generated curriculum.
    errors = []
    suberrors = []
    i = 0
    while i < len(curriculum[0]):
        
        for j in range(n):
            lst = [curriculum[j][i], curriculum[j][i + 1], curriculum[j][i + 2], curriculum[j][i + 3]]
            for s, k in enumerate(lst):
                if lst.count(k) > 1:
                    suberrors.append(j + i)
                    break
        i += 4
    arr = np.array(curriculum)
    arr = arr.transpose()
    for q, j in enumerate(arr[2]):
        for w, k in enumerate(j):
            if list(j).count(k) > 1:
                errors.append([w, q])
                break
    return [errors, suberrors]


def Mutate(curriculum, errors):     #Change the curriculum to resolve conflicts.
    if len(errors) == 1:
        for j in range(3):
            gy = random.randint(8,11)
            curriculum[j][errors[0]], curriculum[j][gy] = curriculum[j][gy], curriculum[j][errors[0]]
    for i in range(len(errors)):
        for j in range(3):
            gy = random.randint(4,7)
            curriculum[j][errors[i]], curriculum[j][i + gy] = curriculum[j][i + gy], curriculum[j][errors[i]]
    return


def Crossover(curriculum, errors): #Performs crossover operations to resolve conflicts.
    for i in errors:
        curriculum[i[0]][i[1]], curriculum[i[0]][random.randint(0, 19)] = curriculum[i[0]][random.randint(0, 19)], curriculum[i[0]][i[1]]
    return


def Output(curriculum, n):        #Writes the generated curriculum to a file named "curriculum.txt".
    f = open("curriculum.txt", "w")
    i = 0
    if len(curriculum) < 19:
        while i < len(curriculum[0]):
            if i == 0: f.write("Monday:\n")
            elif i == 4: f.write("Tuesday:\n")
            elif i == 8: f.write("Wednesday:\n")
            elif i == 12: f.write("Thursday:\n")
            elif i == 16: f.write("Friday:\n")
            if i % 4 == 0: f.write("1 lesson:\n")
            elif i % 4 == 1: f.write("2 lesson:\n")
            elif i % 4 == 2: f.write("3 lesson:\n")
            elif i % 4 == 3: f.write("4 lesson:\n")
            for j in range(n):
                for k in curriculum[j][i]:
                    if k == 'Reading' or k == 'PT' or k == 'English' or k == 'Arts':
                        f.write(k + '\t\t')
                    else: f.write(k + '\t')
                f.write('\n')
            i += 1
    f.close()


f = open('SubList.txt', 'r')
n = 3
data = []
for line in f:
    data.append(line.strip().split(', '))
f.close()
print(data)
while 1:
    curriculum = []
    i = 0
    while i < n:
        curriculum.append(MakeCurriculumForClass(data[i][0], n))     #generate a curriculum for each class.
        i += 1
    while CountCoincidence(curriculum, n)[0] != []:                  #while conflicts!=0
        Crossover(curriculum, CountCoincidence(curriculum, n)[0])     
    temp = curriculum[:]
    while CountCoincidence(curriculum, n)[1] != []:
        if i > 100:
            break
        Mutate(curriculum, CountCoincidence(curriculum, n)[1])
       
        i += 1
    if CountCoincidence(curriculum, n)[1] == []: break
    else: continue

Output(curriculum, n)