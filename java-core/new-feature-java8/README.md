### java8 新特性学习
#### 【总览】
##### 一些重要的Java 8功能是:
- forEach() method in Iterable interface
- default and static methods in Interfaces
- Functional Interfaces and Lambda Expressions
- Java Stream API for Bulk Data Operations on Collections
- Java Time API
- Collection API improvements
- Concurrency API improvements
- Java IO improvements
- Miscellaneous Core API improvements

#### 【详情】
##### 1.forEach() method in Iterable interface(Iterable接口中的forEach（）方法)
每当我们需要遍历Collection时，我们需要创建一个Iterator，其目的是迭代，然后我们在循环中为Collection中的每个元素提供业务逻辑。如果没有正确使用迭代器，会抛出异常ConcurrentModificationException。

Java 8在java.lang.Iterable接口中引入了forEach方法，这样在编写代码时我们只关注业务逻辑。 forEach方法将java.util.function.Consumer对象作为参数，因此它有助于将我们的业务逻辑放在我们可以重用的单独位置。让我们通过简单的例子看看每个用法。

    List<IntegermyList = new ArrayList<Integer>();
    for(int i=0; i<10; i++) myList.add(i);

    //使用iterator
    Iterator<Integeriterator = myList.iterator();
    while (iterator.hasNext()) {
        Integer next = iterator.next();
        System.out.println("Iterator Value::" + next);
    }

    //foreach + 匿名类
    myList.forEach(new Consumer<Integer>() {

        public void accept(Integer t) {
            System.out.println("forEach anonymous class Value::"+t);
        }

    });

    //使用consumer 接口
    MyConsumer action = new MyConsumer();
    myList.forEach(action);

    //使用lambda表达式
    myList.forEach(System.out::println);

##### 2.default and static methods in Interfaces(接口中的默认和静态方法)
jdk8之前，interface方法不能有实现，但是从Java 8开始，接口被增强为具有实现方法。我们可以使用default和static关键字来创建具有方法实现的接口。例如Iterable接口中的forEach方法实现是

    default void forEach(Consumer<? super Taction) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
	}
我们知道Java不会在类中提供多重继承，因为它会导致 Diamond Problem。那么现在如何处理接口，因为接口现在类似于抽象类。解决方案是编译器将在此场景中抛出异常，我们将不得不在实现接口的类中提供实现逻辑。
##### 3.Functional Interfaces and Lambda Expressions（function接口和Lambda表达式）
如果你注意到上面的接口代码，你会注意到@FunctionalInterface注释。功能接口是Java 8中引入的新概念。**只有一个抽象方法的接口就变成了功能接口**。我们不需要使用@FunctionalInterface注释将接口标记为功能接口。 @FunctionalInterface注释是一种避免在功能界面中意外添加抽象方法的工具。您可以将其视为@Override注释，并且最佳实践是使用它。实例：java8 的runnable run接口，带有一个抽象方法:

    @FunctionalInterface
	public interface Runnable {
	    public abstract void run();
	}
功能接口的主要好处之一是可以使用lambda表达式来实例化它们。我们可以使用匿名类实例化一个接口，但代码看起来很笨重。

	//使用匿名类实例化
	Runnable runnable = new Runnable() {
	    @Override
	    public void run() {
	        System.out.println("My Runnable");
	    }
    };
由于功能接口只有一个方法，因此lambda表达式可以很容易地提供方法实现。我们只需要提供方法参数和业务逻辑。例如，我们可以使用lambda表达式将上面的实现编写为：


    //使用lambda表达式
    Runnable runnable1 = () -System.out.println("My Runnable");
    runnable.run();
    runnable1.run();
如果在方法实现中有单个语句，我们也不需要花括号。例如，上面的Interface1匿名类可以使用lambda实例化，如下所示：

    Interface1 interface1 = (s) -System.out.println(s);
    interface1.method1("interface1 method");

**【lambda表达式扩展】**
 Java 中的 Lambda 表达式通常使用 (argument) -(body) 语法书写，例如：
	
	(arg1, arg2...) -{ body }
	(type1 arg1, type2 arg2...) -{ body }
Lambda 表达式的结构

- 一个 Lambda 表达式可以有零个或多个参数
- 参数的类型既可以明确声明，也可以根据上下文来推断。例如：(int a)与(a)效果相同
- 所有参数需包含在圆括号内，参数之间用逗号相隔。例如：(a, b) 或 (int a, int b) 或 (String a, int b, float c)
- 空圆括号代表参数集为空。例如：() -42
- 当只有一个参数，且其类型可推导时，圆括号（）可省略。例如：a -return a*a
- Lambda 表达式的主体可包含零条或多条语句
- 如果 Lambda 表达式的主体只有一条语句，花括号{}可省略。匿名函数的返回类型与该主体表达式一致
- 如果 Lambda 表达式的主体包含一条以上语句，则表达式必须包含在花括号{}中（形成代码块）。匿- - 名函数的返回类型与代码块的返回类型一致，若没有返回则为空

**【函数式接口扩展】**
函数式接口是只包含一个抽象方法声明的接口,可以使用@FunctionalInterface标记
	
###### JDK 8之前已有的函数式接口
- java.lang.Runnable
- java.util.concurrent.Callable
- java.security.PrivilegedAction
- java.util.Comparator
- java.io.FileFilter
- java.nio.file.PathMatcher
- java.lang.reflect.InvocationHandler
- java.beans.PropertyChangeListener
- java.awt.event.ActionListener
- javax.swing.event.ChangeListener

###### 新定义的函数式接口
####### java.util.function中定义了几组类型的函数式接口以及针对基本数据类型的子接口。
- Predicate -传入一个参数，返回一个bool结果， 方法为boolean test(T t)
- Consumer -传入一个参数，无返回值，纯消费。 方法为void accept(T t)
- Function -传入一个参数，返回一个结果，方法为R apply(T t)
- Supplier -无参数传入，返回一个结果，方法为T get()
- UnaryOperator -一元操作符， 继承Function,传入参数的类型和返回类型相同。
- BinaryOperator -二元操作符， 传入的两个参数的类型和返回类型相同， 继承BiFunction。

【示例】

	Predicate<Integerpredicate = (i) -i 0;
	Consumer<Integerconsumer = (i) -System.out.println("consumer : " + i);
	Function<Integer,Booleanfunction = (i) -i 0;
	Supplier<Integersupplier = () -1;
	UnaryOperator<IntegerunaryOperator = (i) -i * i;
	BinaryOperator<IntegerbinaryOperator = (i1,i2) -i1 * i2;
	
	System.out.println(predicate.test(10));
	consumer.accept(10);
	System.out.println(function.apply(10));
	System.out.println(supplier.get());
	System.out.println(unaryOperator.apply(100));
	System.out.println(binaryOperator.apply(100,200));

##### 4.Java Stream API for Bulk Data Operations on Collections(用于集合上的批量数据操作的Java Stream API)
参考[Java 8 中的 Streams API 详解](https://www.ibm.com/developerworks/cn/java/j-lo-java8streamapi/index.html "Java 8 中的 Streams API 详解")

以下是示例代码
    //从 Collection 和数组
    List<Integerlist = new ArrayList<>();
    for(int i=0;i<100;i++) {
        list.add(i);
    }
    Stream<Integerstream = list.stream(); //串行流
    Stream<Integerstream1 = list.parallelStream(); //并行流
    Stream<Integerstream2 = Arrays.stream(list.toArray(new Integer[0]));
    Stream<Integerstream3 = Stream.of(list.toArray(new Integer[0]));

    //从 BufferedReader
    BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("path")));
    Stream<Stringstream4 = bufferedReader.lines();

    //静态工厂
    IntStream stream5 = IntStream.rangeClosed(1, 100);//生成1-100 的int stream
    Stream<Pathstream6 = Files.walk(Paths.get("path"), 100);

    //自己构建 通过StreamSupport辅助类从spliterator产生流
    Stream<Integerstream7 = StreamSupport.stream(list.spliterator(), false);

    //其它
    Random random = new Random();
    IntStream stream8 = random.ints();

    BitSet bitSet = BitSet.valueOf(new long[]{1L, 2L, 3L});
    IntStream stream9 = bitSet.stream();

    Pattern pattern = Pattern.compile("\\d+");
    Stream<Stringstream10 = pattern.splitAsStream("111sda123sda");

    JarFile jarFile = new JarFile("xxx.jar");
    Stream<JarEntrystream11 = jarFile.stream();

##### 5.方法引用
方法引用提供了非常有用的语法，可以直接引用已有Java类或对象（实例）的方法或构造器。与lambda联合使用，方法引用可以使语言的构造更紧凑简洁，减少冗余代码。
下面，我们以定义了4个方法的Car这个类作为例子，区分Java中支持的4种不同的方法引用。[官方文档](https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html "官方文档")

- 第一种方法引用是构造器引用，它的语法是Class::new，或者更一般的Class< T >::new。请注意构造器没有参数。


    final Car car = Car.create(Car::new);
    final List<Carcars = Arrays.asList(car);
- 第二种方法引用是静态方法引用，它的语法是Class::static_method。请注意这个方法接受一个Car类型的参数。


    cars.forEach(Car::collide);
- 第三种方法引用是特定类的任意对象的方法引用，它的语法是Class::method。请注意，这个方法没有参数。


	cars.forEach( Car::repair );
- 最后，第四种方法引用是特定对象的方法引用，它的语法是instance::method。请注意，这个方法接受一个Car类型的参数


    final Car police = Car.create(Car::new);
    cars.forEach(police::follow);

##### 6.重复注解
自从Java 5引入了注解机制，这一特性就变得非常流行并且广为使用。然而，使用注解的一个限制是相同的注解在同一位置只能声明一次，不能声明多次。Java 8打破了这条规则，引入了重复注解机制，这样相同的注解可以在同一地方声明多次。

重复注解机制本身必须用@Repeatable注解。事实上，这并不是语言层面上的改变，更多的是编译器的技巧，底层的原理保持不变。让我们看一个快速入门的例子：

	public class RepeatingAnnotations {
	
	    @Target( ElementType.TYPE )
	    @Retention( RetentionPolicy.RUNTIME )
	    public @interface Filters {
	        Filter[] value();
	    }
	
	    @Target( ElementType.TYPE )
	    @Retention( RetentionPolicy.RUNTIME )
	    @Repeatable( Filters.class )
	    public @interface Filter {
	        String value();
	    }
	
	    @Filter( "filter1" )
	    @Filter( "filter2" )
	    public interface Filterable {
	    }
	
	    public static void main(String[] args) {
	        for( Filter filter: Filterable.class.getAnnotationsByType( Filter.class ) ) {
	            System.out.println( filter.value() );
	        }
	    }
	}

正如我们看到的，这里有个使用@Repeatable( Filters.class )注解的注解类Filter，Filters仅仅是Filter注解的数组，但Java编译器并不想让程序员意识到Filters的存在。这样，接口Filterable就拥有了两次Filter（并没有提到Filter）注解。

同时，反射相关的API提供了新的函数getAnnotationsByType()来返回重复注解的类型（请注意Filterable.class.getAnnotation( Filters.class )经编译器处理后将会返回Filters的实例）。

##### 7.更好的类型推测机制
Java 8在类型推测方面有了很大的提高。在很多情况下，编译器可以推测出确定的参数类型，这样就能使代码更整洁。让我们看一个例子：

	package com.javacodegeeks.java8.type.inference;
	 
	public class Value< T {
	    public static< T T defaultValue() { 
	        return null; 
	    }
	     
	    public T getOrDefault( T value, T defaultValue ) {
	        return ( value != null ) ? value : defaultValue;
	    }
	}
这里是Value< String >类型的用法。
	package com.javacodegeeks.java8.type.inference;
	 
	public class TypeInference {
	    public static void main(String[] args) {
	        final Value< String value = new Value<>();
	        value.getOrDefault( "22", Value.defaultValue() );
	    }
	}
##### 8.扩展注解的支持
Java 8扩展了注解的上下文。现在几乎可以为任何东西添加注解：局部变量、泛型类、父类与接口的实现，就连方法的异常也能添加注解。下面演示几个例子：
	package com.javacodegeeks.java8.annotations;
	 
	import java.lang.annotation.ElementType;
	import java.lang.annotation.Retention;
	import java.lang.annotation.RetentionPolicy;
	import java.lang.annotation.Target;
	import java.util.ArrayList;
	import java.util.Collection;
	 
	public class Annotations {
	    @Retention( RetentionPolicy.RUNTIME )
	    @Target( { ElementType.TYPE_USE, ElementType.TYPE_PARAMETER } )
	    public @interface NonEmpty {        
	    }
	         
	    public static class Holder< @NonEmpty T extends @NonEmpty Object {
	        public void method() throws @NonEmpty Exception {           
	        }
	    }
	         
	    @SuppressWarnings( "unused" )
	    public static void main(String[] args) {
	        final Holder< String holder = new @NonEmpty Holder< String >();       
	        @NonEmpty Collection< @NonEmpty String strings = new ArrayList<>();       
	    }
	}
ElementType.TYPE_USE和ElementType.TYPE_PARAMETER是两个新添加的用于描述适当的注解上下文的元素类型。在Java语言中，注解处理API也有小的改动来识别新增的类型注解。

##### 9.Optional
到目前为止，臭名昭著的空指针异常是导致Java应用程序失败的最常见原因。以前，为了解决空指针异常，Google公司著名的Guava项目引入了Optional类，Guava通过使用检查空值的方式来防止代码污染，它鼓励程序员写更干净的代码。受到Google Guava的启发，Optional类已经成为Java 8类库的一部分。

Optional实际上是个容器：它可以保存类型T的值，或者仅仅保存null。Optional提供很多有用的方法，这样我们就不用显式进行空值检测。更多详情请参考官方文档。

我们下面用两个小例子来演示如何使用Optional类：一个允许为空值，一个不允许为空值。

    //如果Optional类的实例为非空值的话，isPresent()返回true，否从返回false。
    // 为了防止Optional为空值，orElseGet()方法通过回调函数来产生一个默认值。map()函数对当前Optional的值进行转化，
    // 然后返回一个新的Optional实例。orElse()方法和orElseGet()方法类似，但是orElse接受一个默认值而不是一个回调函数。
    Optional< String fullName = Optional.ofNullable( null );
    System.out.println( "Full Name is set? " + fullName.isPresent() );
    System.out.println( "Full Name: " + fullName.orElseGet( () -"[none]" ) );
    System.out.println( fullName.map( s -"Hey " + s + "!" ).orElse( "Hey Stranger!" ) );
另一个例子：

    Optional< String firstName = Optional.of( "Tom" );
    System.out.println( "First Name is set? " + firstName.isPresent() );
    System.out.println( "First Name: " + firstName.orElseGet( () -"[none]" ) );
    System.out.println( firstName.map( s -"Hey " + s + "!" ).orElse( "Hey Stranger!" ) );
    System.out.println();

	


