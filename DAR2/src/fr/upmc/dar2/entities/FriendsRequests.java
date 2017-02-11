package fr.upmc.dar2.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Proxy;

@Entity
@Proxy(lazy = false)
public class FriendsRequests {

		@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;

		@OneToOne
		protected User from;

		@OneToOne
		protected User to;


		public FriendsRequests(){
			super();
		}

		public FriendsRequests(User from,User to){
			this.from=from;
			this.to=to;
		}

		public int getId(){
			return id;
		}

		public User getFrom(){
			return from;
		}

		public User getTo(){
			return to;
		}
}
