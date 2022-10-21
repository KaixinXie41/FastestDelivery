# FastDeliveryApp
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

![WeChat Screenshot_20221020081225](https://user-images.githubusercontent.com/112971217/196960819-cef8f182-d4f0-4d47-b4cc-3e4b2422e318.png)
![WeChat Screenshot_20221020081322](https://user-images.githubusercontent.com/112971217/196960821-be0492d2-4c41-4c57-bfbb-e6ba0ab845ea.png)
![WeChat Screenshot_20221020081335](https://user-images.githubusercontent.com/112971217/196960823-05af65f6-9759-4bc2-b33f-e034c8b0f796.png)
![WeChat Screenshot_20221020081844](https://user-images.githubusercontent.com/112971217/196960830-e3e0b8b7-3d36-4a45-babe-2b07235d1ae2.png)
![WeChat Screenshot_20221020081938](https://user-images.githubusercontent.com/112971217/196960832-478343c7-31d5-4777-b07d-97b29b1b8bc8.png)
![WeChat Screenshot_20221020082042](https://user-images.githubusercontent.com/112971217/196960833-a59d3205-5bb1-473f-ade6-d7ec1b69ea6c.png)
![WeChat Screenshot_20221020082122](https://user-images.githubusercontent.com/112971217/196960834-1a540364-cbad-44cd-adb3-9a1508e0af6e.png)
![WeChat Screenshot_20221020082132](https://user-images.githubusercontent.com/112971217/196960835-bf4c0b5b-3617-45ce-afc6-55209d34df34.png)
![WeChat Screenshot_20221020082200](https://user-images.githubusercontent.com/112971217/196960837-2a5e2610-88c3-4b92-ad1f-fa280c1cce28.png)
![WeChat Screenshot_20221020082329](https://user-images.githubusercontent.com/112971217/196960839-89d9c5a9-260d-4cbd-82b9-a6612739d1b0.png)





