insert into employee(id, name , address, email) values(1,'Jack','USA','jack@gmail.com');

--
-- OAuth2 Required Table Data
--

INSERT INTO app_role (id, role_name, description) VALUES (1, 'STANDARD_USER', 'Standard User - Has no admin rights');
INSERT INTO app_role (id, role_name, description) VALUES (2, 'ADMIN_USER', 'Admin User - Has permission to perform admin tasks');

--
-- USERS john.doe,admin,mbasa
-- non-encrypted password: hello
--
INSERT INTO app_user (id, first_name, last_name, password, password_plain, username) VALUES (1, 'John', 'Doe', '$2y$12$AfAM5Ee.Us/T6fdz.7fX9eBHixv9UA8gtMrgcNXBkhjOxnWt/8jby', 'hello', 'john.doe');
INSERT INTO app_user (id, first_name, last_name, password, password_plain, username) VALUES (2, 'Admin', 'Admin', '$2y$12$AfAM5Ee.Us/T6fdz.7fX9eBHixv9UA8gtMrgcNXBkhjOxnWt/8jby', 'hello', 'admin');
INSERT INTO app_user (id, first_name, last_name, password, password_plain, username) VALUES (3, 'Mario', 'Basa',  '$2y$12$AfAM5Ee.Us/T6fdz.7fX9eBHixv9UA8gtMrgcNXBkhjOxnWt/8jby', 'hello','mbasa');


INSERT INTO user_role(user_id, role_id) VALUES(1,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,2);
INSERT INTO user_role(user_id, role_id) VALUES(3,1);
INSERT INTO user_role(user_id, role_id) VALUES(3,2);

--
-- insert client details
-- non-encrypted client_secret: hello
--
INSERT INTO oauth_client_details
   (client_id, client_secret_plain, client_secret, scope, authorized_grant_types,
   authorities, access_token_validity, refresh_token_validity)
VALUES
   ('testjwtclientid', 'hello', '$2y$12$AfAM5Ee.Us/T6fdz.7fX9eBHixv9UA8gtMrgcNXBkhjOxnWt/8jby', 'read,write', 'password,refresh_token,client_credentials,authorization_code', 'ROLE_CLIENT,ROLE_TRUSTED_CLIENT', 6048800, 259200000);
INSERT INTO oauth_client_details
   (client_id, client_secret_plain, client_secret, scope, authorized_grant_types,
   authorities, access_token_validity, refresh_token_validity)
VALUES
   ('ANDROID_APP', 'hello', '$2y$12$AfAM5Ee.Us/T6fdz.7fX9eBHixv9UA8gtMrgcNXBkhjOxnWt/8jby', 'read,write', 'password,refresh_token,client_credentials,authorization_code', 'ROLE_CLIENT,ROLE_TRUSTED_CLIENT', 6048800, 259200000);
INSERT INTO oauth_client_details
   (client_id, client_secret_plain, client_secret, scope, authorized_grant_types,
   authorities, access_token_validity, refresh_token_validity)
VALUES
   ('test', 'hello', '$2y$12$AfAM5Ee.Us/T6fdz.7fX9eBHixv9UA8gtMrgcNXBkhjOxnWt/8jby', 'read,write', 'password,refresh_token,client_credentials,authorization_code', 'ROLE_CLIENT,ROLE_TRUSTED_CLIENT', 6048800, 259200000);   
ALTER TABLE oauth_access_token ADD CONSTRAINT user_client_const UNIQUE (user_name,client_id);
