
package com.bootcamp.rest.controller;

import com.bootcamp.jpa.entities.Programme;
import com.bootcamp.jpa.entities.User;
import com.bootcamp.jpa.enums.RoleUser;
import com.bootcamp.jpa.repositories.UserRepository;
import com.bootcamp.jpa.repositories.ProgrammeRepository;
import com.bootcamp.json.jsonClasses.ClassesToJson;
import com.bootcamp.rest.exception.AuthentificationException;
import com.bootcamp.rest.exception.NotCreateException;
import com.bootcamp.rest.exception.SuccessMessage;
import com.bootcamp.rest.exception.TokenNotGenerateException;
import com.bootcamp.rest.exception.TokenVerifyException;
import com.bootcamp.rest.exception.ReturnResponse;
import com.bootcamp.rest.exception.UnknownException;
import com.bootcamp.rest.otherClasses.UserManager;
import com.bootcamp.rest.security.JavaJsonWebToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/programme")
public class ProgrammeRestController {
    
    UserRepository ur = new  UserRepository("tpJpa-mysql");
    ProgrammeRepository pr = new ProgrammeRepository("tpJpa-mysql");
    
    JavaJsonWebToken jt = new JavaJsonWebToken();
    
    List<Programme> liste = new ArrayList<Programme>();
    
    ObjectMapper om = new ObjectMapper();
    
    Response resp;
    
    ClassesToJson ctj = new ClassesToJson();
    
    UserManager um = new UserManager();    
        
    @POST
    @Path("/login")
    @Consumes("application/json")
	@ApiOperation(
			value="connexion",
			notes="Vous connecter !",
			response=User.class,
			responseContainer="User"
			)
	@ApiResponses({
	     @ApiResponse(code=201, message="Connexion avec succès"),
	     @ApiResponse(code=404, message="impossible de ce connecter")
	})
    public Response login(User obj) throws SQLException {  
        String iat = "BootcampToken";
        long tm = 900000; // 15 min
       User user = new User();
        try {
            user = ur.findByLoginAndPwd(obj.getLogin(), obj.getPwd());
            um.setUser(user);
            //
            
            try {          
                String subject = user.toString();
                jt.createJWT(iat, subject, tm);
                resp = SuccessMessage.message("Retenez bien ce token: \n"+jt.getToken()+"\n"+user.toString());
        } catch (Exception e) {
                resp = TokenNotGenerateException.generateTokenException();
         }
        } catch (Exception e) {
            resp = AuthentificationException.auth("Erreur lors de l'authentifiacation ! \n verifiez vos infos :  ",obj);
        }
        
        return resp;
        
    }
    
    @GET
    @Path("/all")
	@ApiOperation(
			value="afficher",
			notes="Afficher la liste des programmes",
			response=Programme.class,
			responseContainer="Programme"
			)
	@ApiResponses({
	     @ApiResponse(code=201, message="Affichage avec succès"),
	     @ApiResponse(code=404, message="impossible d'afficher ce programme")
	})
    //@Produces(MediaType.APPLICATION_JSON)
    public Response getAllProrammes() {
        
        try {
            jt.parseJWT(jt.getToken());
            SuccessMessage.message("Token Valide");
            try {
                liste = pr.findAll();
                
                //conversion en string json
                String json = ctj.simpleConvertObjectToJson(liste);
                resp = ReturnResponse.object("",json);
            } catch (Exception e) {
            }
            
        } catch (Exception e) {
            resp =TokenVerifyException.tokenException(jt.getToken());
         }
        return resp;
    }
    
   
    
    @POST
    @Path("/create")
	@ApiOperation(
			value="create",
			notes="Ajouter un nouveau programme",
			response=Programme.class,
			responseContainer="Programme"
			)
	@ApiResponses({
	     @ApiResponse(code=201, message="Ajout avec succès"),
	     @ApiResponse(code=404, message="impossible d'ajouter ce programme")
	})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Programme programme) throws SQLException {
        //
        RoleUser role =  um.getUser().getRole();
        try {
           jt.parseJWT(jt.getToken());          
            //
            if(role.equals(RoleUser.ADMIN) == true){
                //
                 try {
                
                pr.create(programme);
                resp=SuccessMessage.message("Bien cree");
            } catch (Exception e) {
                resp=NotCreateException.notCreateException("Erreur lors de la creation", e);
            }
            }else {
              resp=AuthentificationException.auth("Token Valide\n Mais Vous n'etes pas autorise a creer une instance.  Desole !! \n",role);
            }
                                 
        } catch (Exception e) {
            resp =TokenVerifyException.tokenException(jt.getToken());
         }
        return resp;
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(
			value="update",
			notes="Mettre a jour un  programme",
			response=Programme.class,
			responseContainer="Programme"
			)
	@ApiResponses({
	     @ApiResponse(code=201, message="update avec succès"),
	     @ApiResponse(code=404, message="impossible de mettre a jour ce programme")
	})
    public Response update(Programme programme) throws SQLException {
        try {
            jt.parseJWT(jt.getToken());
            resp =SuccessMessage.message("Token Valide");
            try {
                pr.update(programme);
                resp=SuccessMessage.message("Mise a jour bien faite");
            } catch (Exception e) {
                resp=NotCreateException.notCreateException("Erreur lors de la mise a jour", e);
            }
            
        } catch (Exception e) {
            resp =TokenVerifyException.tokenException(jt.getToken());
         }
        
        return resp;
    }
    
    @DELETE
    @Path("/delete/{id}")
	@ApiOperation(
			value="delete",
			notes="Supprimer un nouveau programme",
			response=Programme.class,
			responseContainer="Programme"
			)
	@ApiResponses({
	     @ApiResponse(code=201, message="suppression avec succès"),
	     @ApiResponse(code=404, message="impossible de supprimer ce programme")
	})
    public Response delete(@PathParam("id") int valeur) throws SQLException {
        RoleUser role = um.getUser().getRole();
        try {
           jt.parseJWT(jt.getToken());          
            //
            if(role.equals(RoleUser.ADMIN) == true) {
                          
                try {
                Programme programme = pr.findById(valeur);
                pr.delete(programme);
                resp=SuccessMessage.message("Le programme d'id "+valeur+" a bien ete supprime");
            } catch (Exception e) {
                resp=NotCreateException.notCreateException("Probleme lors de la suppression de programme d'id"+valeur,e);
            }
                
            } else {
//           resp=AuthentificationException.auth("Token Valide \n Mais Vous n'etes pas autorise a Supprimer une instance.  Desole !! \n");
            }         
        } catch (Exception e) {
            resp =TokenVerifyException.tokenException(jt.getToken());
         }
        
        return resp;    
    }
    
    @GET
    @Path("/{id}")
	@ApiOperation(
			value="afficher",
			notes="Afficher un nouveau programme",
			response=Programme.class,
			responseContainer="Programme"
			)
	@ApiResponses({
	     @ApiResponse(code=201, message="Affichage avec succès"),
	     @ApiResponse(code=404, message="impossible d'afficher ce programme")
	})
    @Produces(MediaType.APPLICATION_JSON)
    public Response findProgrammeByFields(@PathParam("id") long id,@QueryParam("fields") String fields) throws SQLException, IntrospectionException, InvocationTargetException, IllegalArgumentException, IllegalAccessException {
        try {
           jt.parseJWT(jt.getToken());
            try {
        Field[] fieldsProgramme = Programme.class.getDeclaredFields();
        
        String[] rempli = fields.split(",");
        Programme prog = pr.findById(id);
        
         Map<String, Object> responseMap = new HashMap<String, Object>();
         
         PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(Programme.class).getPropertyDescriptors();
       
         for (PropertyDescriptor propertyDescriptor: propertyDescriptors) {

           Method method = propertyDescriptor.getReadMethod();

           if (check(rempli, propertyDescriptor.getName())) {
               responseMap.put(propertyDescriptor.getName(), method.invoke(prog));
           }
                   
       }
         // Concersion en string json
         String json = ctj.simpleConvertObjectToJson(responseMap);
         resp = ReturnResponse.object("", responseMap);
                  
            } catch (Exception e) {
         resp=UnknownException.unknownException(e);
            }
            
        } catch (Exception e) {
         resp =TokenVerifyException.tokenException(jt.getToken());
         }    
        
       return resp;        
    }
    
    
    
     private boolean check(String[] fields, String field) {

       for (String field1 : fields) {
           if (field.equals(field1)) {
               return true;
           }
       }
       return false;
   }
          

//  E X P E R I M E N T A T I O N
   
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)//ou @Produces("application/json")
    public Response getList() throws IOException, SQLException  {
        liste = pr.findAll();
        return Response.status(200).entity(ctj.simpleConvertObjectToJson(liste)).build();        
        }
    
    }
