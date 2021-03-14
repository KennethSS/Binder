<h1 align="center">Binder</h1></br>

<p align="center">
🗂 Binder is a simple RecyclerView with databinding and mvvm pattern for andorid listview
</p>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
</p>

### Dependency Gradle 
Add below codes to your **root** `build.gradle` file (not your module build.gradle file).
```gradle
allprojects {
    repositories {
        jcenter()
        maven { url "https://jitpack.io" }
    }
}
```

And add a dependency code to your **module**'s `build.gradle` file.
```gradle
buildFeatures {
  dataBinding true
}
```

```gradle
dependencies {
  implementation 'com.github.KennethSS:Binder:1.0.1'
}
```


## Usage


#### Prapare Item
```xml
<layout>
    <data>
        <variable
            name="item"
            type="com.some.package.Food" />

        <variable
            name="vm"
            type="com.some.package.FoodViewModel" />
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:onClick="@{view->vm.onClick(item)}">

        <androidx.appcompat.widget.AppCompatImageView
            android:src="@{item.img}"/>

        <TextView
            android:text="@{item.title}"/>
    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```

```kotlin
data class Food (
    val title: String,
    val subtitle: String,
    val img: Int,
    override val layoutRes: Int = R.layout.item_food
) : ItemType
```

#### Set Adater
```kotlin
class FoodAdapter(
    lifecycleOwner: LifecycleOwner,
    viewModelList: ViewModelList<Food>,
    viewModel: FoodViewModel
) : AbstractDataBindingAdapter<Food>(viewModelList, viewModel)
```

#### Set Adater for paging
```kotlin
class FoodLoadingAdapter(
    lifecycleOwner: LifecycleOwner,
    viewModelList: ViewModelPagingList<Food>,
    controller: RecyclerViewController,
    viewModel: FoodViewModel
) : AbstractLoadingAdapter<Food>(viewModelList, controller, viewModel)
```

### Observe in adapter
```kotlin
class FoodLoadingAdapter() {
    init {
        viewModelList.list.observe(lifecycleOwner, {
            notifyDataSetChanged()
        })
    }
}
```

#### Set ViewModel List
```kotlin
class FoodListViewModel : ViewModel(), ViewModelList<Food> {
    private val _list = MutableLiveData<List<Food>>()
    override val list: LiveData<List<Food>>
    
    fun fetchFoodList() {
        val items...
        _list.postValue(items) // set items for list
    }
}
```

#### Set ViewModel List for Paging
```kotlin
class FoodListMoreViewModel : ViewModel(), ViewModelPagingList<Food>, PagingListener {
    private val _list = MutableLiveData<List<Food>>()
    override val list: LiveData<List<Food>>
        get() = _list
    override var isPaging: Boolean = true

    override fun fetchNextPage() {
        val newFoodList = FoodFactory.getFoodList(5)
        val currentList = (list.value ?: arrayListOf())
        _list.postValue(currentList + newFoodList)

        if (foods.last().title == "Luttuce combo" || newFoodList.isEmpty()) {
          isPaging = false // Set end paging
        }
    }
}
```

#### Set Binder 
```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val recyclerView = findViewById<RecyclerView>(R.id.food_list_view)
        
        // For list
        recyclerView.adapter = FoodAdapter(lifecycleOwner, foodListViewModel, foodViewModel)

        // For paging
        val recyclerViewController = RecyclerViewController(recyclerView, foodListMoreViewModel)

        recyclerView.adapter = FoodLoadingAdapter(
            lifecycleOwner,
            foodListMoreViewModel,
            recyclerViewController,
            foodViewModel
        )

        // Fetch list to ViewModel
        foodListViewModel.fetchFoodList()
    }
}
```

### Todo
- Blink issue
