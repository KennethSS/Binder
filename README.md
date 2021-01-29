<h1 align="center">Binder</h1></br>

<p align="center">
Binder is a simple RecyclerView with databinding and mvvm pattern for andorid listview
</p>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
</p>

### Dependency Gradle 
Add below codes to your **root** `build.gradle` file (not your module build.gradle file).
```
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
  implementation 'com.github.KennethSS:Binder:1.0.0'
}
```


## Usage
### Basic Example

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
    override val layoutRes: Int = R.layout.item_food_menu
) : ItemType
```




#### Set ViewModel
```kotlin
class FoodListViewModel : ViewModel(), ViewModelList<Food> {
    private val _list = MutableLiveData<Int>()
    override val list: LiveData<List<Food>>

    init {
        list = _list.switchMap {
            liveData(Dispatchers.IO) {
                val foods = FoodFactory.getFoodList(5)
                emit(foods)
            }
        }
    }

    @MainThread
    fun fetchFoodList() {
        _list.value = 0
    }
}
```

#### Set Adater
```kotlin
class FoodAdapter(
    lifecycleOwner: LifecycleOwner,
    viewModelList: ViewModelList<Food>,
    viewModel: FoodViewModel
) : AbstractDataBindingAdapter<Food>(viewModelList, viewModel) {

    init {
        viewModelList.list.observe(lifecycleOwner, {
            notifyDataSetChanged()
        })
    }
}
```

#### Set Adater for paging
```kotlin
class FoodLoadingAdapter(
    lifecycleOwner: LifecycleOwner,
    viewModelList: ViewModelPagingList<Food>,
    controller: RecyclerViewController,
    viewModel: FoodViewModel
) : AbstractLoadingAdapter<Food>(viewModelList, controller, viewModel) {
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
    private val _list = MutableLiveData<Int>()
    override val list: LiveData<List<Food>>

    init {
        list = _list.switchMap {
            liveData(Dispatchers.IO) {
                val foods = FoodFactory.getFoodList(5)
                emit(foods)
            }
        }
    }

    @MainThread
    fun fetchFoodList() {
        _list.value = 0
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

    private fun getFoodList() {
        Thread {
            sleep(2000)
            val foods = FoodFactory.getFoodList(5)
            val currentList = (list.value ?: arrayListOf())
            _list.postValue(currentList + foods)

            if (foods.last().title == "Luttuce combo") {
                isPaging = false // Set end paging
            }
        }.start()
    }

    override fun fetchNextPage() {
        getFoodList()
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

<p align="center">
  <img width="320" src="https://user-images.githubusercontent.com/39362939/95305625-32dd3400-08c1-11eb-88b3-92be623a5aca.gif">
</p>
