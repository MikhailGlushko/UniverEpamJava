# UniverExternalJava17Aut4
2017, Осінь, Київ, Зовнішні курси, Java, Група 4

Аудиторна та домашня робота по курсу

0 Основи Java
  - Фігури (https://github.com/MikhailGlushko/UniverEpamJava/tree/master/Task0(02.11.2017)/src/ua/epam)

1 Масиви
  - Впорядкувати одновимірний масив размістив спочатку відємні за зростанням, потім добатні за спаданням (https://github.com/MikhailGlushko/UniverEpamJava/blob/master/Task1(03.11.2017)/src/ua/epam/arrays/Task1.java)
  - В одновимірному масиві розмістити спочатку додатні, потім відємні за О(n) (https://github.com/MikhailGlushko/UniverEpamJava/blob/master/Task1(03.11.2017)/src/ua/epam/arrays/Task2.java)
  - Дано цілочисельну прямокутну матрицю. Впорядкувати стовбці за зменшенням середнього значення (https://github.com/MikhailGlushko/UniverEpamJava/blob/master/Task1(03.11.2017)/src/ua/epam/arrays/Task3.java)
  - Дано цілочисельну прямокутну матрицю. Впорядкувати стрічки, за зростанням самої довшої послідовностіоднакових елементів (https://github.com/MikhailGlushko/UniverEpamJava/blob/master/Task1(03.11.2017)/src/ua/epam/arrays/Task4.java)
  - Дано квадратну матрицю A порядку M (M — непарне число). Починаючи з елемента A1,1 і рухаючись за годинниковою стрілкою, вивести всі елементи по спіралі: перша стрічка, останній стовбець, остання стрічка в зворотньому порядку,  перший стовбець в зворотньому порядку,  елементи другої стрічки, що залишилися і т.д.; останнім виводиться центральний елемент матриці (https://github.com/MikhailGlushko/UniverEpamJava/blob/master/Task1(03.11.2017)/src/ua/epam/arrays/Task5.java)
  -  Написати програму, що знаходить прохід по лабіринту. Лабіринт задано у вигляді матриці квадратів. Кожен квадрат або відкритий або закритий. Входити в закритий квадрат заборонено. Якщо квадат відкритий, то в нього можна зайти тільки зі сторони, з кута - заборонено. Кожен квадрат задається його координатами в матриці. Програма находить прохід через лабіринт, рухаючись від заданого входу. Після знаходження шляху програма виводить його у вигляді координат квадратів. Для зберігання шляху використовувати стек (https://github.com/MikhailGlushko/UniverEpamJava/blob/master/Task1(03.11.2017)/src/ua/epam/maze/TestMazeNew.java)

2 Створити абстрактний клас CVehicle. На його основі реалізувати класи CPlane, CCar, CShip. Класи повинні мати можливість задавати та отримувати координати, параметри засобів переміщення (ціна, швидкість, рік випуску). Для сомольота повинна бути визначена висота польоту, для самоьота та корабля - кількість пасажирів. Для корабля - порт приписки. Написати програму, що створює список обєктів, вказаних класів в динамічній памяті. Програма повинна містити меню, за допомогою якого, можна виконати перевірку всіх методів класів.
  - механізми з найменшою ціною з найбільшою швидкістю і не старші 5 років (https://github.com/MikhailGlushko/UniverEpamJava/blob/master/Task2(07.11.2017)/src/ua/epam/TestVehicle01.java)
  - Знайти із механізмів Plane з висотою польоту вище 5000 з роком випуску після 2000 (https://github.com/MikhailGlushko/UniverEpamJava/blob/master/Task2(07.11.2017)/src/ua/epam/TestVehicle02.java)
  - ЗНайти мехаанізми з максимальною швидкістю в діапазоні 200 - 500, но не Plane (https://github.com/MikhailGlushko/UniverEpamJava/blob/master/Task2(07.11.2017)/src/ua/epam/TestVehicle03.java)
  - Добавити до даної ієрархії машину-амфібію, и БетМобіль, створити 3 масиви згрупованих за Інтерфейсами Flyable, MoveAble, SwimAble (https://github.com/MikhailGlushko/UniverEpamJava/blob/master/Task2(07.11.2017)/src/ua/epam/TestVehicle04.java)

3 Добавити в проект 2 підтримку maven (https://github.com/MikhailGlushko/UniverEpamJava/blob/master/Task3(09.11.2017)/pom.xml)

4 ООП (https://github.com/MikhailGlushko/UniverEpamJava/tree/master/Lab4(14.11.2017)/src/main/java/ua/epam)
  - створити послідовність з фігур, використовуючи фабрики кольорових та не кольорових фігур;
  - отримати масиви кольорових та не кольорових фігур і скільки кожних фігур в ньому є ();
  - фігури в масивах погруповати ();
  - протестувати методи фігур.

5 Написати консольну програму, що відповідає наступним вимогам:
  - Використати можливості ООП: класи, наслідування, поліморфізм, інкапсуляцію.
  - Кожен клас повинен мати змістовну назву та інформативний вміст.
  - наслідування повинно щастосовуватися тільки тоді, коли в цьому є потреба.
  - При дуванні  необхідно дотримуватися домовленностей оформлення коду java code convention.
  - Класи повинні бути грамотно розміщені по пакетам.
  - Работа з онсолью або консольне меню повинні бути мінімальними.
  - Для зберігання параметрів ініціалізації можно використовувати файли
  - **Завдання**:	Домашні електроприбори. Розробити ієрархію ектроприборів. Включити деякі в розетку. підрахувати споживаючу потужність. Провести сотрування приборів в квартирі на основі потужності. Знайти прибор в квартирі, який відповідає заданому набору параметрів. (https://github.com/MikhailGlushko/UniverEpamJava/blob/master/Lab5(16.11.2017)/src/main/java/ua/epam/appliance/main/DemoApp.java) 
  - фримен page 71 - реалізовати патерн спостерігая(Weather Station) + добавити считування данних через інтернет для датчиків (https://github.com/MikhailGlushko/UniverEpamJava/blob/master/Lab5(16.11.2017)/src/main/java/ua/epam/weatherstation/main/WeatherStationDemo.java)

6 Стрічки. Регулярні вирази
  - Написать регулярное выражение, определяющее является ли данная строчка валидным URL адресом. В данной задаче правильным URL считаются адреса http и https, явное указание протокола также может отсутствовать. Учитываются только адреса, состоящие из символов, т.е. IP адреса в качестве URL не присутствуют при проверке. Допускаются поддомены, указание порта доступа через двоеточие, GET запросы с передачей параметров, доступ к подпапкам на домене, допускается наличие якоря через решетку. Однобуквенные домены считаются запрещенными. Запрещены спецсимволы, например «–» в начале и конце имени домена. Запрещен символ «_» и пробел в имени домена. При составлении регулярного выражения ориентируйтесь на список правильных и неправильных выражений заданных ниже. Пример правильных выражений: http://www.zcontest.ru, http://zcontest.ru. Пример неправильных выражений:  Just TextElement, http://a.com. (https://github.com/MikhailGlushko/UniverEpamJava/blob/master/Lab6(23.11.2017)/src/main/java/ua/epam/string/regexp/DemoApp.java)
  - Написать поисковый сервис: по произвольному URL перейти и спомощью регулярного выражения найти 20 URL каждому указываете ключевые слова с частотами по указаному слову; найти все URL упорядоченые по возрастанию частоты слова (https://github.com/MikhailGlushko/UniverEpamJava/blob/master/Lab6(23.11.2017)/src/main/java/ua/epam/string/searching/DemoApp.java)
  - Создать программу обработки текста учебника по программированию с использованием классов: Символ, Слово, Предложение, Знак препинания и др. Во всех задачах с формированием текста заменять табуляции и последовательности пробелов одним пробелом.
  - **Завдання**: Найти такое слово в первом предложении, которого нет ни в одном из остальных предложений. (https://github.com/MikhailGlushko/UniverEpamJava/blob/master/Lab6(23.11.2017)/src/main/java/ua/epam/string/textprocessor/Text.java)

7 Стековый калькулятор - реализация в 4 главе Роберт Лафоре - Структуры данных и алгоритмы в Java. 2-е издание
  - **Завдання**: добавить sin cos (https://github.com/MikhailGlushko/UniverEpamJava/blob/master/Lab7(24.11.2017)/src/main/java/ua/epam/calc/CalcApp.java)

8

