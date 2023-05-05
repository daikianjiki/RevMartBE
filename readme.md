# RevMart - Backend

RevMart is an ecommerce app where a user can register and login to their account.
Once logged in, they are able to look through available list of products and are able to add or remove products from the cart.
Each product has a price field. When a user adds a product into their cart, the balance field of the user will increase or decrease per price of the product this being added or removed.
Since there are no payment gateway for this project, a user can empty their cart to imitate a cash-out process.
<hr>

### Project duration: 
- 1 week

### Integrated Development Environment (IDE): 
- IntelliJ

### Project Requirement: 
- Spring, SpringBoot, SpringData, Spring MVC
- Unit Tests using JUnit & Mockito
- Custom Exceptions & Exception Handler

### Prerequisites
- JDK 17 or later
- Apache Maven

### Installation/Steps
1. Clone the repository: [RevMartBE Repository](https://github.com/daikianjiki/RevMartBE)
2. Open in IDE
3. Run RevMartApplication
4. Use Postman/Swagger to check the endpoints: check API Reference

### API Reference
<table>
  <thead>
    <tr>
      <th></th>
      <th>HTTP Method</th>
      <th>Endpoint</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Register a user</td>
      <td>POST</td>
      <td>localhost:9000/user/register</td>
    </tr>
    <tr>
      <td>Login a user</td>
      <td>POST</td>
      <td>localhost:9000/user/login</td>
    </tr>
    <tr>
      <td>View all products</td>
      <td>GET</td>
      <td>localhost:9000/product</td>
    </tr>
    <tr>
      <td>View a product</td>
      <td>GET</td>
      <td>localhost:9000/product/1</td>
    </tr>
    <tr>
      <td>Add a product to user's cart</td>
      <td>POST</td>
      <td>localhost:9000/user/1/addProduct/1</td>
    </tr>
    <tr>
      <td>Remove a product from user's cart</td>
      <td>POST</td>
      <td>localhost:9000/user/1/removeProduct/1</td>
    </tr>
    <tr>
      <td>Empty a user's cart</td>
      <td>PATCH</td>
      <td>localhost:9000/user/1/emptyCart</td>
    </tr>
    <tr>
      <td>View a user</td>
      <td>GET</td>
      <td>localhost:9000/user/1</td>
    </tr>
  </tbody>
</table>

### Resources
- [Basic formatting for readme](https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax)
- [How to write a unit test with JUnit](https://www.infoworld.com/article/3543268/junit-5-tutorial-part-2-unit-testing-spring-mvc-with-junit-5.html)
