<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
    <section class="footer" >
    <div class="container">
    	<div class="row">
        	<div class="col-lg-4  col-md-4 col-sm-4">
            	<div class="footer_dv">
                	<h4>Les Meet-ups</h4>
                	<ul>
                    	<li class="line_rv"><a href="/DAR/events?mode=new">Créer des évenements</a></li>
               			<li><a href="/DAR/jsp/eventSearch.jsp">Rechercher un évenement</a></li>
                        <li><a href="/DAR/events?mode=showall">Consulter la liste </a></li>
                        <li><a id="myLink" title="Evénements Proches" href="#" onclick="nearEvents();">Evénements proches</a></li>
                        
                    </ul>
                </div>
            </div>
            <div class="col-lg-4  col-md-4 col-sm-4">
            	<div class="footer_dv">
                	<h4>Social</h4>
                	<ul>
 		<li class="line_rv"><a  id ="friends" href=" javascript: void(0);">Liste d'amis</a></li>
               			<li><a href="/DAR/jsp/finduser.jsp">Rechercher </a></li>
                        <li><a>Partager ! </a></li>
                       
           		<li>
                <a href="https://www.facebook.com/sharer/sharer.php?u=http://elysane.net:8080/DAR/home"><i class="fa fa-facebook-square fa-3x social"></i></a>
	            <a href="https://twitter.com/share?url=http://elysane.net:8080/DAR/home"><i class="fa fa-twitter-square fa-3x social"></i></a>
	           
				</li>
                       
                        
                    </ul>
                </div>
            </div>
            <div class="col-lg-4  col-md-4 col-sm-4">
            	<div class="footer_dv">
                	<h4>Uni-Connect</h4>
                	<p>Projet DAR 2016</p>
      			<p>UPMC M2 STL PROMO 2016/2017<br>
				
                </p></div>
            </div>
        </div>
    </div>
</section>
<script type="text/javascript">
		$(document)
				.ready(
						function() {

							$("#friends").click(
									function() {

										$(".maincontainer").load(
												'/DAR/jsp/myfriends.jsp ');
									});
						});
									
									</script>