# SecondProjectByMVVM
It's a basic function Food Order App which using Api and RoomDataBase under MVVM architecture with Kotlin language by Android Studio Software.

#This App contain below function:

1. Search Meal By Different Category/Area/Ingredient/Namet.
   
   |—— Set up by different Api Filter, click Floating button to change fragment in same frameLayout to search by specific part.
 
2. Put meal into meal cart.  

   |—— At CartAdapter get product by mealId throw bundle arguments and when cartFragment created  will get all cart meal by ViewModel getCartMealByMealId   
   
3. Create and save Address by user.  
   
   |—— When customer choose AddAddress Button, show new Fragment, set Save button to AddressDao to save EditText value
   
4. Create new order by shopping cart check out systems.
   
   |—— Check out Systems include 4 diffrent function at below:
       
       |——Check out cart product list
                 
       |——Check out Delivery option screen
                 
       |——Check out Payment option screen
               
       |—— Check out Summary sceen
   
5. Register/Login/Logout Function Throw Firebase Authentication
   
   |—— Register: check customer type in information valid or not then using presenter to apply user information by Firebase Authentication Tools                          
   
   |—— Login:    submit user type in email and password throw Firebase Login Process , if success, intent to MainActivity and passing user data also create User Account in Room Database.
   
   |—— Logout:   logout function is one navigation drawer menu option allow user to click it and go back to Entry Activity.
   
   //Todo: Support Chat

#Build with

- [Kotlin](https://kotlinlang.org/) 

- [Android Architecture Components](https://medium.com/@myofficework000/mvvm-architecture-using-repository-pattern-for-beginners-181a5df3fff8) 

- [GSON](https://github.com/google/gson)

- [Denpendencies Library] (https://docs.google.com/document/d/1EP81MKUFQsPWHvAdPzTk9hnWWzV4aKsB7O-93qzGbaU/edit)

- [Glide](https://github.com/bumptech/glide) 

- [Android Navigation Components] (https://developer.android.com/guide/navigation/navigation-getting-started)

- [Room DataBase] (https://medium.com/@myofficework000/room-database-in-android-42c68df845df)

#Project Structure 

com.example.secondprojectbymvvm    

|—— model

    |—— data
      
       |—— category
      
       |—— meal
      
       |—— order
    
    |—— local
    
      |—— address
      
      |—— cart
      
      |—— restaurant
      
      |—— user

       
|—— view

   |—— authentication
   
   |—— checkout
   
       |—— checkout
       
       |—— deliveryoption
       
       |—— order
       
   |—— foodtracking
   
   |—— homepage
   
       |—— search
   
   |—— mealitemlist
       
       |—— area
       
       |—— category
       
       |—— ingredient
       
       |—— mealdetails

|—— viewModel

com.example.secondprojectbymvvm(androidTest)

|—— dao

com.example.secondprojectbymvvm(test)

|—— view.authentication

|—— viewModel






