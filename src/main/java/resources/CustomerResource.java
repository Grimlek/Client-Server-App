package resources;

import services.CustomerService;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Customer;

/**
 * Created by csexton on 7/18/16.
 */
@Path("/customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

	private CustomerService custService;
	private ObjectMapper mapper;

	public CustomerResource() {
		this.custService = new CustomerService();
		this.mapper = new ObjectMapper();
	}

	@GET
	@Path("/{id}")
	public Response getCustomerId(@PathParam("id") String customerId) {
		try {
			Customer customer = custService.findById(Integer.parseInt(customerId));
			if (customer == null) {
				return Response.status(Response.Status.NOT_FOUND).entity("The following id wasn't found: " + customerId)
						.build();
			}
			return Response.ok(customer).build();
		} catch (NumberFormatException e) {
			return Response.status(Response.Status.SEE_OTHER)
					.entity("You must enter a number ------   Error  -----     : " + customerId
							+ "\n------------------------------------------------------------------------\n"
							+ e.getMessage())
					.build();
		}
	}

	@POST
	@Path("/{id}")
	public Response updateCustomer(String body, @PathParam("id") String customerId) {
		try {
			Customer customer = custService.findById(Integer.parseInt(customerId));
			mapper.readerForUpdating(customer).readValue(body);
			custService.update(customer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.OK).entity("body = " + body).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteCustomer(@PathParam("id") String customerId) {
		Customer customer = custService.findById(Integer.parseInt(customerId));
		custService.delete(customer);
		return Response.status(Response.Status.OK).build();
	}

	@PUT
	@Path("")
	public Response createCustomer(String body) {
		try {
			List<Customer> customers = mapper.readValue(body, new TypeReference<List<Customer>>() { });
			for (Customer customer : customers) {
				custService.create(customer);
			}
		} catch (JsonParseException e) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("An error occurred will parsing the JSON! Please double check the format of the JSON."
							+ "\n------------------------------------------------------------------------\n"
							+ e.getMessage())
					.build();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.OK).build();
	}

	@GET
	@Path("")
	public Response searchCustomer(@QueryParam("address") String address, @QueryParam("phoneNum") String phoneNum) {
		List<Customer> customers;
		if (phoneNum != null) {
			customers = custService.findByPhoneNum(phoneNum);
		} else if (address != null) {
			address = address.replaceAll("-", " ");
			customers = custService.findByAddress(address);
		} else {
			return Response.status(Response.Status.BAD_REQUEST).entity("You must use the correct URL pattern.").build();
		}
		return Response.status(Response.Status.OK).entity(customers).build();
	}
}
