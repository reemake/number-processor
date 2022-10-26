## RESTfull API service для обработки последовательности чисел.
Программа должна позволят считать массив целых чисел из текстового файла, обработать и выдать необходимые результаты. Результаты запрашиваются по средством REST-запросов, информация о файле передается в виде json (например):  
{  
	"file_path": "C:/test_data/10m.txt"  
}  

Числа в файле разделены между собой с помощью специального символа "\n" (каждое число на новой строке). 

В сервисе необходимо реализовать следующие операции (REST-запросы), возвращающие:  
1. максимальное число в файле;
2. минимальное число в файле;
3. медиана;
4. среднее арифметическое значение;
5. самая длинная последовательность идущих подряд чисел, которая увеличивается;
6. самая длинная последовательность идущих подряд чисел, которая уменьшается.

Тип запрашиваемой операции может передаваться как параметр в json ("operation": "get_max_value"), так и определяться из URL запроса (http://../get_max_value).

Результат выдается так же в виде json (пример):  
{  
	" max_value ": "777777"  
}  

### Найденные значения из тестового файла **10m.txt**: 
- Максимальное число = 49999978  
- Минимальное число = -49999996  
- Медиана = 25216  
- Среднее арифметическое = 7364.418442641844  
- Самая длинная последовательность идущих подряд чисел, которая увеличивается = [-48190694, -47725447, -43038241, -20190291, -17190728, -6172572, 8475960, 25205909, 48332507, 48676185]  
- Самая длинная последовательность идущих подряд чисел, которая уменьшается = [47689379, 42381213, 30043880, 12102356, -4774057, -5157723, -11217378, -23005285, -23016933, -39209115, -49148762]

### Примеры REST-запросов
- Передача файла в бинарном виде:  
  
Request:  
![image](https://user-images.githubusercontent.com/90447198/198011262-f03825b0-0e22-40f0-b12a-0a80cc344e9e.png)  
Response:  
![image](https://user-images.githubusercontent.com/90447198/198011528-1ada6110-dc90-4e45-be86-6459a90d4096.png)  
  
  
- Чтение файла по переданному пути:  
  
Request:  
![image](https://user-images.githubusercontent.com/90447198/198011842-319191cc-3677-48d2-93c0-3c11600760d9.png)  
Response:  
![image](https://user-images.githubusercontent.com/90447198/198011937-62c5b4d3-92a2-4013-85e3-9c00b4fed0e8.png)
  
  
- Получение максимума (через URL запрос):  
  
Request:  
![image](https://user-images.githubusercontent.com/90447198/198012532-cff5d8f5-aff4-4ea4-a3b8-746692f1ac8a.png)  
Response:  
![image](https://user-images.githubusercontent.com/90447198/198012694-7e0604f7-ffe2-4245-8d46-a659f17001b4.png)
  
  
- Получение минимума (через URL запрос):  
  
Request:  
![image](https://user-images.githubusercontent.com/90447198/198015245-0ccfd148-4ff3-4641-b32e-6d0032b8461b.png)  
Response:  
![image](https://user-images.githubusercontent.com/90447198/198015296-04f4e705-c7e8-4a6c-873e-e4fa62b30b33.png)
  
  
- Получение медианы (через URL запрос):  
  
Request:  
![image](https://user-images.githubusercontent.com/90447198/198013189-9098cccc-fd9b-4aa0-a92c-f9ad565d2596.png)  
Response:  
![image](https://user-images.githubusercontent.com/90447198/198013268-828e3527-70f1-44d7-8071-743b379e1872.png)  
  
  
- Получение среднего арифметического (через параметр в JSON):  
  
Request:  
![image](https://user-images.githubusercontent.com/90447198/198015637-b800ae98-8db4-4e0d-98eb-f55f212b5ca3.png)  
Response:  
![image](https://user-images.githubusercontent.com/90447198/198015753-9764c2bc-260a-4340-a707-b9eef7c7fdaa.png)
  
  
- Получение макс. возрастающей последовательности (через параметр в JSON):  
  
Request:  
![image](https://user-images.githubusercontent.com/90447198/198016013-318af03f-05b5-4de7-9205-332c1b8366f8.png)  
Response:  
![image](https://user-images.githubusercontent.com/90447198/198016091-6b0a385a-4662-4a0b-998c-8591d1cf6523.png)
  
  
- Получение макс. убывающей последовательности (через параметр в JSON):  
  
Request:  
![image](https://user-images.githubusercontent.com/90447198/198013498-0c38fd9a-ac16-4029-86c2-728719bea19c.png)  
Response:  
![image](https://user-images.githubusercontent.com/90447198/198013954-1cb5256f-90f6-4479-ba1d-74a178a9567e.png)







