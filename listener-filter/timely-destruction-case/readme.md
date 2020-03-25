# 案例-定时销毁 `session`

定时销毁 `session`。例如，销毁 `5` 秒钟没操作的 `session`。

1. 怎样可以将每一个创建的 `session` 全都保存起来？

   我们可以做一个 `HttpSessionListener`，当 `session` 对象创建时，就将这个 `session` 对象装入到一个集合中。

   将集合 `List<HttpSession>` 保存到 `ServletContext` 域中。

   路径：[MyServletContextListener](src/main/java/org/lzn/listener/MyServletContextListener.java)

2. 怎样可以判断 `session` 过期了?

   在 `HttpSession` 中有一个方法 `public long getLastAccessedTime()`。
   它可以得到 `session` 对象最后使用的时间。
   可以使用 `invalidate` 方法销毁。

   路径：[MySessionListener](src/main/java/org/lzn/MySessionListener.java)

3. 如何使用任务调度功能？

   路径：[TestTimer](src/main/java/org/lzn/timer/TestTimer.java)

   ```java
   public class TimerDemo {
       public static void main(String[] args) {
           // 创建一个定时器
           Timer t = new Timer();
           t.schedule(new TimerTask() {
               @Override
               public void run() {
                   System.out.println(new Date().toLocaleString());
               }
           }, 1000, 1000);
       }
   }
   ```

   