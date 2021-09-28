package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public abstract class BaseServlet {

    private static final long serialVersionUID = 1L;


    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        /*
         * Установите POST и GET кодировку запроса
         */
        /*
         * 1. Получить параметры, используемые для определения метода, который пользователь хочет запросить
         * метод - метод, который пользователь хочет вызвать в адресной строке браузера, который является параметром
         */
        String methodName = request.getParameter("POST");
        // 2. Определяем, передал ли пользователь параметры.
        if(methodName == null || methodName.trim().isEmpty()){
            throw new RuntimeException("Вы не передали параметр метода! Невозможно определить метод, который вы хотите вызвать!");
        }
        /**
         * 3. Определите, какой метод используется, а какой называется
         * Используемая технология: отражение
         * Нужно получить этот класс Class, а затем вызвать его getMethod, получить объект Method, передав имя метода
         * Цель этого заключается в следующем: если вы хотите изменить или добавить новые методы, такие как другие методы сервлета, в будущем, вам не нужно изменять этот фрагмент кода.
         */
        Method method = null ;
        try {
            /**
             * Результат метода: общедоступный java.lang.String cn.kgc.servlet.client.loOrreServlet.login (javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse) выдает javax.letioException.Serv. IOException
             * Обратите внимание на два момента в следующем коде:
             * 1. Поскольку getClass () определяется как final в классе Object, подклассы не могут переопределить этот метод,
             * Таким образом, здесь вызывается метод getClass () в классе Object, который эквивалентен super,
             * (Возвращаемое значение метода getClass () - это объект Class текущего класса времени выполнения,
             * Таким образом, getClass () не зависит от этого и супер, но определяется текущим классом выполнения. )
             * 2. Метод getMethod () может получать только публично модифицированные методы, поэтому модификаторы доступа методов, унаследованных от таких сервлетов, должны быть открытыми
             * Если вы хотите получить доступ ко всем методам, измените getMethod на getDeclaredMethod
             *
             */
            //3.1 Получить метод метод
            method = this.getClass().getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Метод, который вы хотите вызвать"+methodName+", Этого не существует");
        }
        try {
            /*
             * 3.2. Вызов метода, представленного методом, то есть: вызов метода в классе, который наследует этот класс
             * Отражение вызова: используйте это для вызова метода, представленного методом, и передачи параметров req и resp
             * Возвращаемое значение результата определяется унаследованным возвращаемым значением, например: return "f: /pages/user/login_success.jsp";
             */
            String result =(String)method.invoke(this, request,response);
            System.out.println("result:"+result);
            /**
             * 4. Обработка строк, возвращаемых из классов, которые наследуют этот класс (перенаправление и пересылка)
             * вернуть «r: /index.jsp» и вернуть «f: /index.jsp»;
             * Возвращаемая строка является строкой, которую нужно интерпретировать
             */
            /*
             * 4.1. Если возвращаемая пользователем строка имеет значение null или "", то ничего не делать
             */
            if(result == null || result.trim().isEmpty()){
                return  ;
            }
            /*
             * 4.2. Интерпретация строки 1: определите, есть ли в строке двоеточие
             * Нет двоеточия означает пересылку по умолчанию, в противном случае судить
             */
            if(result.contains(":")){
                /*
                 * 4.3 Интерпретация строки 2: сначала получить позицию двоеточия, затем перехватить префикс (операция, перенаправление или пересылка) и суффикс (путь)
                 */
                int index = result.indexOf(":");
                String operate = result.substring(0,index);
                String path = result.substring(index+1);
                /*
                 * 4.4 Процесс, если он перенаправлен r, если f, то forward
                 */
                if(operate.equalsIgnoreCase("r")){
                    response.sendRedirect(request.getContextPath()+path);
                }else if(operate.equalsIgnoreCase("f")){
                    request.getRequestDispatcher(path).forward(request, response);
                }else{
                    throw new RuntimeException("Операция, которую вы указали"+operate+
							"Не поддерживается, пожалуйста, заполните правильно: r и f");
                }
            }else{
                /*
                 * Нет обработки пересылки по умолчанию двоеточия
                 */
                request.getRequestDispatcher(result).forward(request, response);
            }
        } catch (Exception e) {
            System.out.println("Метод, который вы хотите вызвать"+methodName+"Это внутреннее исключение");
            throw new RuntimeException(e);
        }
    }
}
