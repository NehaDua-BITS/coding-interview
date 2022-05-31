package round1;

import org.interview.round1.exceptions.ErrorCodes;
import org.interview.round1.exceptions.InvalidInputException;
import org.interview.round1.exceptions.RouterException;
import org.interview.round1.service.impl.DefaultRouter;
import org.junit.Assert;
import org.junit.Test;

public class DefaultRouterTest {

    @Test
    public void testAddRoute() {
        DefaultRouter defaultRouter = new DefaultRouter();
        String expectedMethodName = "getUsers";
        defaultRouter.addRoute("/users", expectedMethodName);
        String actualMethodName = defaultRouter.route("/users");
        Assert.assertEquals(expectedMethodName, actualMethodName);
    }

    @Test(expected = InvalidInputException.class)
    public void testAddRouteException() {
        DefaultRouter defaultRouter = new DefaultRouter();
        defaultRouter.addRoute("/users", "getUsers");
        defaultRouter.addRoute("/users/id", "getUser");
        defaultRouter.addRoute("/users", "method");
    }


    @Test
    public void testGetRouteForException() {
        DefaultRouter defaultRouter = new DefaultRouter();
        InvalidInputException exception = Assert.assertThrows(InvalidInputException.class, () -> defaultRouter.route("/users"));
        Assert.assertEquals(ErrorCodes.ROUTE_NOT_FOUND.getMsg(), exception.getErrorMsg());
        Assert.assertEquals(ErrorCodes.ROUTE_NOT_FOUND.getCode(), exception.getErrorCode());
    }

    @Test
    public void testGetForPathVariable() {
        DefaultRouter defaultRouter = new DefaultRouter();
        String expectedMethodName = "getUser";
        defaultRouter.addRoute("/users/{id}", expectedMethodName);
        ;
        RouterException exception = Assert.assertThrows(InvalidInputException.class, () -> {
            defaultRouter.route("/users/456/neha");
        });
        Assert.assertEquals(ErrorCodes.ROUTE_NOT_FOUND.getMsg(), exception.getErrorMsg());
        Assert.assertEquals(ErrorCodes.ROUTE_NOT_FOUND.getCode(), exception.getErrorCode());
    }

    @Test(expected = InvalidInputException.class)
    public void testForMultipleRoutes() {
        DefaultRouter defaultRouter = new DefaultRouter();
        defaultRouter.addRoute("/products", "getProducts");
        defaultRouter.addRoute("/products/{id}", "getProduct");
        defaultRouter.addRoute("/products/abcd", "getProduct");
        defaultRouter.addRoute("/products/{id}/price", "getProductPrice");
        defaultRouter.addRoute("/products/{id}/inventory", "getProductInventory");

        Assert.assertEquals("getProducts", defaultRouter.route("/products"));
        Assert.assertEquals("getProduct", defaultRouter.route("/products/123"));
        Assert.assertEquals("getProductPrice", defaultRouter.route("/products/456/price"));
        Assert.assertEquals("getProductInventory", defaultRouter.route("/products/123/inventory"));

        Assert.assertEquals("getProductInventory", defaultRouter.route("/products/123/inventory/123"));

    }
}
