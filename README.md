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

#### Prepare to making item
```xml
<layout>    
    <data>
        <!-- If you wan't to use that you have not to declare -->
        <variable
            name="vm"
            type="com.solar.recyclerviewsample.viewmodel.FoodViewModel" />
    </data>
  
    <com.solar.recyclerview.SolarRecyclerView
        android:id="@+id/list_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
        bind:loading="@{true}"
        bind:vm="@{vm}"
        bind:items="@{vm.foodGridList}"
        bind:decoration="@{6}"
        tools:listitem="@layout/item_food_grid">
    </com.solar.recyclerview.SolarRecyclerView>
</layout>
```

#### Prapare Item
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
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // For list
        findViewById<RecyclerView>(R.id.food_list_view).let {
            it.adapter = FoodAdapter(lifecycleOwner, foodListViewModel, foodViewModel)
        }

        // For paging
        findViewById<RecyclerView>(R.id.food_list_view).let {
            val recyclerViewController = RecyclerViewController(it, foodListMoreViewModel)
            val adapter = FoodLoadingAdapter(
                lifecycleOwner,
                foodListMoreViewModel,
                recyclerViewController,
                foodViewModel
            )
            it.adapter = adapter
        }
        
        foodListViewModel.fetchFoodList()
    }
}
```

<p align="center">
  <img width="320" src="https://user-images.githubusercontent.com/39362939/95305625-32dd3400-08c1-11eb-88b3-92be623a5aca.gif">
</p>
