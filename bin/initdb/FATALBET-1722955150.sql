CREATE TABLE IF NOT EXISTS "USERS" (
	"ID" uuid NOT NULL UNIQUE,
	"USERNAME" varchar(255) NOT NULL,
	"NICKNAME" varchar(255) NOT NULL,
	"EMAIL" varchar(255) NOT NULL UNIQUE,
	"PASSWORD" varchar(255) NOT NULL,
	"BALANCE" numeric(10,0) NOT NULL,
	"DELETED" boolean NOT NULL,
	"ROLE" varchar(255) NOT NULL,
	"CREATE_AT" timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"UPDATE_AT" timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY ("ID")
);

CREATE TABLE IF NOT EXISTS "ADMIN_LOGS" (
	"ID" serial NOT NULL UNIQUE,
	"ADMIN_ID" uuid NOT NULL,
	"ACTION" varchar(255) NOT NULL,
	"LOG_DATE" timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY ("ID")
);

CREATE TABLE IF NOT EXISTS "TRANSACTIONS" (
	"ID" serial NOT NULL UNIQUE,
	"USER_ID" uuid NOT NULL,
	"TYPE" varchar(255) NOT NULL,
	"STATUS" bigint NOT NULL,
	"AMOUNT" numeric(10,0) NOT NULL,
	"CREATE_AT" timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
	"UPDATED_AT" timestamp with time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY ("ID", "USER_ID")
);

CREATE TABLE IF NOT EXISTS "BETS" (
	"ID" serial NOT NULL UNIQUE,
	"USER_ID" uuid NOT NULL,
	"AMOUNT" numeric(10,0) NOT NULL,
	"STATUS" varchar(255) NOT NULL,
	PRIMARY KEY ("ID")
);

CREATE TABLE IF NOT EXISTS "BETS_MATCHES" (
	"BET_ID" bigint NOT NULL,
	"MATCH_ID" bigint NOT NULL,
	"SHOT" varchar(255) NOT NULL,
	PRIMARY KEY ("BET_ID", "MATCH_ID")
);


ALTER TABLE "ADMIN_LOGS" ADD CONSTRAINT "ADMIN_LOGS_fk1" FOREIGN KEY ("ADMIN_ID") REFERENCES "USERS"("ID");
ALTER TABLE "TRANSACTIONS" ADD CONSTRAINT "TRANSACTIONS_fk1" FOREIGN KEY ("USER_ID") REFERENCES "USERS"("ID");
ALTER TABLE "BETS" ADD CONSTRAINT "BETS_fk1" FOREIGN KEY ("USER_ID") REFERENCES "USERS"("ID");
ALTER TABLE "BETS_MATCHES" ADD CONSTRAINT "BETS_MATCHES_fk0" FOREIGN KEY ("BET_ID") REFERENCES "BETS"("ID");

CREATE OR REPLACE FUNCTION update_modified_column()
RETURNS TRIGGER AS $$
BEGIN
   NEW."UPDATE_AT" = NOW();
   RETURN NEW;
END;
$$ language 'plpgsql';

CREATE TRIGGER update_users_modtime
BEFORE UPDATE ON "USERS"
FOR EACH ROW
EXECUTE PROCEDURE update_modified_column();

CREATE TRIGGER update_transactions_modtime
BEFORE UPDATE ON "TRANSACTIONS"
FOR EACH ROW
EXECUTE PROCEDURE update_modified_column();


