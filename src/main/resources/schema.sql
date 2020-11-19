CREATE TABLE employee
(
 id int PRIMARY KEY ,
 name text NOT NULL,
 address text DEFAULT NULL,
 email text DEFAULT NULL
);

-- 
-- OAuth2 Required Tables
--

CREATE TABLE app_role (
  id bigint NOT NULL ,
  description varchar(255) DEFAULT NULL,
  role_name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);


CREATE TABLE app_user (
  id bigint NOT NULL ,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  password text NOT NULL,
  password_plain text,
  username varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE user_role (
  user_id bigint NOT NULL,
  role_id bigint NOT NULL,
  CONSTRAINT FK859n2jvi8ivhui0rl0esws6o FOREIGN KEY (user_id) REFERENCES app_user (id),
  CONSTRAINT FKa68196081fvovjhkek5m97n3y FOREIGN KEY (role_id) REFERENCES app_role (id)
);

drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  client_secret_plain VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

drop table if exists oauth_client_token;
create table oauth_client_token (
    token_id VARCHAR(255),
    token bytea ,
    authentication_id VARCHAR(255),
    user_name VARCHAR(255),
    client_id VARCHAR(255)
);

drop table if exists oauth_access_token;
create table oauth_access_token (
    token_id VARCHAR(255),
    token bytea,
    authentication_id VARCHAR(255),
    user_name VARCHAR(255),
    client_id VARCHAR(255),
    authentication bytea,
    refresh_token VARCHAR(255)
);

create index oauth_access_token_id on oauth_access_token(token_id);
create index oauth_refresh_token_id on oauth_access_token(token_id);

drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
    token_id VARCHAR(255),
    token bytea,
    authentication bytea
);

drop table if exists oauth_code;
create table oauth_code (
    code VARCHAR(255), authentication bytea
);

drop table if exists oauth_approvals;
create table oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt TIMESTAMP,
    lastModifiedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

drop table if exists ClientDetails;
create table ClientDetails (
    appId VARCHAR(255) PRIMARY KEY,
    resourceIds VARCHAR(255),
    appSecret VARCHAR(255),
    scope VARCHAR(255),
    grantTypes VARCHAR(255),
    redirectUrl VARCHAR(255),
    authorities VARCHAR(255),
    access_token_validity INTEGER,
    refresh_token_validity INTEGER,
    additionalInformation VARCHAR(4096),
    autoApproveScopes VARCHAR(255)
);
