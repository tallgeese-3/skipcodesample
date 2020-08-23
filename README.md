# Skip Android Coding Challenge

Here at Skip we aim to create world-class app experiences for our customers. Skip takes great care in how we select members of our engineering team. This coding challenge will help us get an idea of how you would use software to solve problems for our end users.

We know that your time is valuable, so we’ve designed the challenge with that in mind. There's no specific amount of time that this challenge should take you to complete, as the level of effort will depend on how familiar you are with Android development, but we don't want it to take you more than an evening. With that in mind, please feel free to spend as much time as you’d like crafting your preferred solution and we hope you have fun building it!

## Challenge: Product Search!

Your challenge is to create a three-screen application that allows users to:
1. Search for products at Best Buy using the [Best Buy Products API](https://bestbuyapis.github.io/api-documentation/#products-api)
2. View their search results in a list
3. View the individual details of each product returned in the search result

The first screen should display an input field that allows users to search for products (for example, “laptop”, “Cell Phone”, “Stainless Steel Ovens”, etc).  Upon executing a search, the next screen should display the results in a list. Clicking an item in the list should launch a details screen. The details screen should show the product's image and provide more details about the product.

### Requirements

1. The main screen should display a search input, and should perform a search against the Best Buy Product Search API when the user submits the query.
2. When a search returns results, these should be displayed in a list format. Each list item should provide, at a minimum, the name of the product (e.g., Google - Pixelbook Pen - Midnight Blue), the sku of the product (e.g., 6306364) and the image from the response. Clicking a list item should launch the details screen for that product.
3. The details screen for a product should include the name of the product, the sku of the product, the image of the product, the longDescription of the product, and a link to the url that should open an external Intent to a browser installed on the device when clicked.
4. The user should be able to navigate between screens according to Android platform conventions.
5. Please zip up your project and email it to <jobs@getskip.com> with your first and last name.

Feel free to use any libraries you feel are appropriate to your needs.

### Notes

To be able to access the Best Buy API you will need an API key. This API should have been sent to you already. If you have not received the API key, please email <jobs@getskip.com> with your name and the request for the API key.

A query to the Best Buy Product API could look something like:

```
curl "https://api.bestbuy.com/v1/products(search=pixel&search=pen)?format=json&show=name,sku,salePrice,image,longDescription,url&apiKey=INSERT_API_KEY"
```

For quick and easy icons if needed, consider using the [Material icons](https://material.io/icons/).

### Considerations

Among other criteria, your submission will be evaluated on:

1. Implementation of the stated requirements
2. Application Architecture
3. The general quality of the code and it’s resistance to crashing
4. Your use of Android coding conventions
5. Knowledge and usage of Android libraries and SDKs
6. Clarity of communications in comments and other documentation
7. UI and UX -- while we don’t want you to spend any time creating custom icons or other assets, the app should look clean and generally obey the Human Interface Guidelines
8. Coding challenge reviewers should be able to load your project into Android Studio and build an instance of your app into an emulator. If the reviewer needs to do any project configuration (i.e. add his/her own Best Buy API key) that is enough reason to reject your application.

Don’t worry if your app has personality or a sense of humor. We want you to have fun!


Sincerely,

Your Friends at Skip

### Sample Best Buy Response

For your convenience, below is a sample JSON response from the Best Buy Products Search API.

```
{
    "from": 1,
    "to": 8,
    "currentPage": 1,
    "total": 8,
    "totalPages": 1,
    "queryTime": "0.053",
    "totalTime": "0.062",
    "partial": false,
    "canonicalUrl": "/v1/products(search=\"pixel\"&search=\"pen\")?show=name,sku,salePrice,image,longDescription,url&format=json&apiKey=INSERT_API_KEY",
    "products": [
        {
            "name": "Google - Pixelbook Pen - Midnight Blue",
            "sku": 6306364,
            "salePrice": 99.00,
            "image": "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6306/6306364_sa.jpg",
            "longDescription": "Write and design with confidence with this Google Pixelbook pen. The responsive instrument can be used with Google Pixelbook or Google Assistant to make taking notes or creating art more efficient. The realistic feel of this Google Pixelbook pen helps you write and draw naturally so you can focus on your designs.",
            "url": "https://api.bestbuy.com/click/-/6306364/pdp"
        },
        {
            "name": "Apple Pencil (1st Generation) - White",
            "sku": 4538802,
            "salePrice": 99.99,
            "image": "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/4538/4538802_sa.jpg",
            "longDescription": "The new Multi-Touch subsystem in the new iPad 9.7\" and iPad Pro gives Apple Pencil striking capabilities alongside pixel perfect precision. Using incredibly sensitive pressure and tilt sensors, Apple Pencil instantly recognizes when you are pressing harder or shifting its angle. So you can vary line weight, create subtle shading, and produce a wide range of artistic effects &#8212; just like with a conventional pencil.",
            "url": "https://api.bestbuy.com/click/-/4538802/pdp"
        },
        {
            "name": "Microsoft - Surface Go 2 - 10.5\" Touch-Screen - Intel Pentium Gold - 4GB - 64GB Storage - Platinum",
            "sku": 6408476,
            "salePrice": 399.99,
            "image": "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6408/6408476_sa.jpg",
            "longDescription": "Your perfect everyday companion. New 10.5\" Surface Go 2 is perfect for keeping up and winding down - delivering tablet portability with laptop versatility, long battery life, a stunning touchscreen, and Windows security for the whole family. Browse, shop, and manage email with ease, relax with your favorite TV shows, and much more.",
            "url": "https://api.bestbuy.com/click/-/6408476/pdp"
        },
        {
            "name": "Microsoft - Surface Go - 10\" Touch-Screen - Intel Pentium Gold - 8GB Memory - 128GB Storage - Silver",
            "sku": 6261397,
            "salePrice": 494.99,
            "image": "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6261/6261397_sa.jpg",
            "longDescription": "New 10&#8221; Surface Go is perfect for all your daily tasks, giving you laptop performance with tablet portability, a stunning touchscreen, and the Windows and Office* experience you know. From email, browsing, and home projects to unwinding with a favorite TV show, Surface Go is by your side wherever you are &#8212; with up to 9 hours&#185; of battery life, built-in HD cameras, hassle-free connectivity, and all the ports you need, including multi-tasking USB-C.  * Sold separately.",
            "url": "https://api.bestbuy.com/click/-/6261397/pdp"
        },
        {
            "name": "Surface Pro X - 13\" Touch Screen - Microsoft SQ1 - 8GB Memory - 256GB SSD - WiFi+4G LTE - Keyboard+Slim Pen - Matte Black",
            "sku": 6375053,
            "salePrice": 1569.98,
            "image": "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6375/6375053_sa.jpg",
            "longDescription": "You're always one step ahead. So is Surface Pro X. The sleek design and ultimate mobility combine with blazing-fast LTE&#178; Advanced Pro connectivity&#179; and an edge-to-edge 13\" touch screen with amazing graphics for creating, mobile gaming, and working away from your desk. For a premium laptop experience on the go, click Surface Pro X Signature Keyboard with Slim Pen (included) in place. The Pen stores securely and recharges in the keyboard, so it's always at your fingertips.",
            "url": "https://api.bestbuy.com/click/-/6375053/pdp"
        },
        {
            "name": "Microsoft - Geek Squad Certified Refurbished Surface Go - 10\" Touch-Screen - Intel Pentium Gold - 4GB Memory - 64GB Storage - Silver",
            "sku": 6410327,
            "salePrice": 323.99,
            "image": "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6410/6410327_sa.jpg",
            "longDescription": "Geek Squad&#174; Certified Refurbished products are thoroughly, painstakingly and lovingly tested, so you can be sure that your device will work right, right away. Learn more about Geek Squad&#174; Certified Refurbished products.Finish pending assignments on the go with this refurbished Microsoft Surface Go tablet. The 10-inch PixelSense multitouch display provides an interactive user experience, while the 64GB SSD offers vast media space and reduced load times. This Microsoft Surface Go tablet features an Intel Pentium Gold dual-core processor and 4GB of RAM for seamless multitasking.",
            "url": "https://api.bestbuy.com/click/-/6410327/pdp"
        },
        {
            "name": "Knights of Pen and Paper Bundle - Nintendo Switch [Digital]",
            "sku": 6321545,
            "salePrice": 22.49,
            "image": "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6321/6321545_sa.jpg",
            "longDescription": "Take on the roles of in-game players by taking on the roles of their characters in a traditional Pen and Paper RPG session in the ultimate meta role-playing experience.  This item cannot be returned or refunded, please visit Best Buy Return Policy to learn more.",
            "url": "https://api.bestbuy.com/click/-/6321545/pdp"
        },
        {
            "name": "Knights of Pen & Paper 2 Deluxiest Edition - Nintendo Switch [Digital]",
            "sku": 6321546,
            "salePrice": 12.99,
            "image": "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6321/6321546_sa.jpg",
            "longDescription": "Prepare to join Knights of Pen & Paper 2 in a turn-based, retro-style, pixel-art adventure full of danger, intrigue, and semi-appropriate cultural references.  This item cannot be returned or refunded, please visit Best Buy Return Policy to learn more.",
            "url": "https://api.bestbuy.com/click/-/6321546/pdp"
        }
    ]
}
```
