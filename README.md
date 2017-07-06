# dldebuglog
一个在Debug模式下利用aspectj库进行切面编程进行日志代码的插入，通过注解触发，查看函数调用顺序、耗时、运行线程等操作的调试工具。
AspectJ探索使用
Join Points：包含调用构造方法，调用普通方法，方法执行，异常等。表示新的代码的插入位置
Pointcuts：在Join Points中通过一定条件选择出我们所需要的Join Points，所以说，Pointcuts，也就是带条件的Join Points，作为我们需要的代码切入点。
Advice：是我们具体插入的代码，以及如何插入这些代码。

切点使用：
源代码如下
public String getName(String first,String last){
	return first+last;
}
@Before为Advice；execution为处理join points的类型如：call，execution；(*android.app.AppActivity.on**(..))，第一个*表示任意类型返回值，后面为包名路径，使用**匹配；可使用&&、||、!进行条件组合。(**)表示任意个任意类型参数；public String getName(String first,String last)为实际切入的代码。
@Before(“execution(*android.app.AppActivity.getName**(..))”)
public void onGetNameBefore(JoinPoint joinPoint){
	String key = joinPoint.getSignature().toSring();
	Log.d(TAG,”onGetNameBefore”+key);
}
@Around(“execution(*android.app.AppActivity.getName**(..))”)
public String onGetName(JoinPoint joinPoint){
	String key = joinPoint.getSignature().toSring();
	Log.d(TAG,”onGetNameBefore”+key);
	joinPoint.proceed();//执行被修饰的原始方法
	Log.d(TAG,”onGetNameAfter”+key);
}
注意：@Around 和 @After不能同时作用在一个方法上
前方高能：
自定义Pointcuts，可以更加精确的切入一个或多个切入点
1.	自定义一个注解类

/**
*自定义AOP注解类
*/
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.CONSTRUCTOR,ElementType.METHOD})
public @Interface DebugTool{
}
2.	需要插入代码的地方使用@DebugTool
3.	创建自己的切入文件
