# Transfer

通过此案例熟悉 `DBUtils`。项目为三层架构，`AccountService` 和 `AccountDao` 各有两个实现类。

## [ThreadLocal](src\main\java\org\lzn\threadlocal\ThestThreadLocal.java)

模拟 `ThreadLocal` 的设计，了解下他的作用。

```java
public class ThreadLocal{
    private Map<Runnable,Object> container = new HashMap<Runnable,Object>();
    public void set(Object value){
        //用当前线程作为key
    	container.put(Thread.currentThread(),value);
    }
    public Object get(){
    	return container.get(Thread.currentThread());
    }
    public void remove(){
    	container.remove(Thread.currentThread());
    }
}

```

**总结：** 调用该类的 `get` 方法，永远返回当前线程放入的数据。线程局部变量。