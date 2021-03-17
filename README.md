# CalendarDayEvent-Android
Day view of calendar and event can be added to any time of the day

## ScreenShot
[![](https://jitpack.io/v/mriduldk/CalendarDayEvent-Android.svg)](https://jitpack.io/#mriduldk/CalendarDayEvent-Android)

<a><img src="./image/Screenshot.png" width="200"></a>

# Install
To get a Git project into your build:

## Gradle
Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:
````gradle
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
````
Step 2. Add the dependency
````Gradle
	dependencies {
	        implementation 'com.github.mriduldk:CalendarDayEvent-Android:0.1.0'
	}
````

## Maven
Step 1. Add the JitPack repository to your build file

	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>


Step 2. Add the dependency

	<dependency>
	    <groupId>com.github.mriduldk</groupId>
	    <artifactId>CalendarDayEvent-Android</artifactId>
	    <version>0.1.0</version>
	</dependency>
  
  
 # How to Use
 
````xml
<in.codingstudio.calendardayevent.CalendarDayEvent
        android:id="@+id/calendarDayEvent"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
````
````Java
	// Create a ToDo ArrayList to store all ToDos
	private ArrayList<ToDo> todos;
	private CalendarDayEvent calendarDayEvent;

	calendarDayEvent = findViewById(R.id.calendarDayEvent);
	
	// It is optional. Default it is 0 to 24  
	calendarDayEvent.setLimitTime(0, 24);

	todos = new ArrayList<>();

	Calendar timeStart = Calendar.getInstance();
	// Set the Starting time of your ToDo Event
	timeStart.set(Calendar.HOUR_OF_DAY, 13); // 13 -> 1 PM
	timeStart.set(Calendar.MINUTE, 0);
	
	// Set the Ending time of your ToDo Event
	Calendar timeEnd = (Calendar) timeStart.clone();
	timeEnd.set(Calendar.HOUR_OF_DAY, 14);
	timeEnd.set(Calendar.MINUTE, 30); // 2:30 PM

	// Create your ToDo
	ToDo to = new ToDo();
	// Topic is Optional. 
	to.setTopic("Topic");
	to.setDescription("Todo Description");
	to.setStartTime(timeStart);
	to.setEndTime(timeEnd);
	// Color determines the color of Topic text and leftside bar color
	to.setColor(getResources().getColor(R.color.orange));

	todos.add(to);
	calendarDayEvent.setTodos(todos);

````

````Kotlin
	// Create a ToDo ArrayList to store all ToDos
	private var todos = ArrayList<ToDo>()
	
	// It is optional. Default it is 0 to 24  
	calendarDayEvent.setLimitTime(0, 23)
	
	// Set the Starting time of your ToDo Event
	val startTime = Calendar.getInstance()
	startTime.set(Calendar.HOUR_OF_DAY, 15) // 15 -> 3PM
	startTime.set(Calendar.MINUTE,0)

	// Set the Ending time of your ToDo Event
	val endTime = Calendar.getInstance()
	endTime.set(Calendar.HOUR_OF_DAY, 16) 
	endTime.set(Calendar.MINUTE,30) // 4:30 PM

	// Create your ToDo
	val todo = ToDo()
	// Topic is Optional. 
	todo.topic = "Topic" // Insert Topic
	// Color determines the color of Topic text and leftside bar color
	todo.color = resources.getColor(R.color.blue)
	
	todo.description = "ToDo Description"
	todo.startTime = startTime
	todo.endTime = endTime

	todos.add(todo)

	calendarDayEvent.setTodos(todos)
````
  
# Contribution
1. Overlapping ToDos are currently not available. You can contribute this feature.
2. If you've found an error, please file an issue.
