CREATE TABLE IF NOT EXISTS "ADMINS" (
	"ID" text NOT NULL UNIQUE,
	PRIMARY KEY ("ID")
);

CREATE TABLE IF NOT EXISTS "USERS" (
	"ID" text NOT NULL UNIQUE,
	"USERNAME" text NOT NULL,
	"NICKNAME" text NOT NULL,
	"EMAIL" text NOT NULL UNIQUE,
	"PASSWORD" text NOT NULL,
	"BALANCE" numeric(10,0) NOT NULL,
	"DELETED" boolean NOT NULL,
	"CREATE_AT" timestamp with time zone NULL,
	"UPDATE_AT" timestamp with time zone NULL,
	PRIMARY KEY ("ID")
);

CREATE TABLE IF NOT EXISTS "ADMIN_LOGS" (
	"ID" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"ADMIN_ID" text NOT NULL,
	"ACTION" text NOT NULL,
	"LOG_DATE" timestamp with time zone NOT NULL,
	PRIMARY KEY ("ID")
);

CREATE TABLE IF NOT EXISTS "TEAMS" (
	"ID" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"NAME" text NOT NULL,
	"LOGO" text NOT NULL,
	PRIMARY KEY ("ID")
);

CREATE TABLE IF NOT EXISTS "TRANSACTIONS" (
	"ID" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"USER_ID" text NOT NULL,
	"TYPE" text NOT NULL,
	"STATUS" bigint NOT NULL,
	"AMOUNT" numeric(10,0) NOT NULL,
	"CREATED_AT" timestamp with time zone NOT NULL,
	"UPDATED_AT" timestamp with time zone NOT NULL,
	PRIMARY KEY ("ID", "USER_ID")
);

CREATE TABLE IF NOT EXISTS "MATCHES" (
	"ID" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"TEAM_HOME" bigint NOT NULL,
	"TEAM_AWAY" bigint NOT NULL,
	"DATE" date NOT NULL,
	"TIME" time without time zone NOT NULL,
	"STATUS" text NOT NULL,
	"RESULT" text NOT NULL,
	"HOME_WIN_ODD" numeric(10,0) NOT NULL,
	"AWAY_WIN_ODD" numeric(10,0) NOT NULL,
	"DRAW_ODD" numeric(10,0) NOT NULL,
	PRIMARY KEY ("ID")
);

CREATE TABLE IF NOT EXISTS "BETS" (
	"ID" bigint GENERATED ALWAYS AS IDENTITY NOT NULL UNIQUE,
	"USER_ID" text NOT NULL,
	"AMOUNT" numeric(10,0) NOT NULL,
	"STATUS" text NOT NULL,
	PRIMARY KEY ("ID")
);

CREATE TABLE IF NOT EXISTS "BETS_MATCHES" (
	"BET_ID" bigint NOT NULL,
	"MATCH_ID" bigint NOT NULL,
	"SHOT" text NOT NULL,
	PRIMARY KEY ("BET_ID", "MATCH_ID")
);

ALTER TABLE "ADMINS" ADD CONSTRAINT "ADMINS_fk0" FOREIGN KEY ("ID") REFERENCES "USERS"("ID");

ALTER TABLE "ADMIN_LOGS" ADD CONSTRAINT "ADMIN_LOGS_fk1" FOREIGN KEY ("ADMIN_ID") REFERENCES "ADMINS"("ID");

ALTER TABLE "TRANSACTIONS" ADD CONSTRAINT "TRANSACTIONS_fk1" FOREIGN KEY ("USER_ID") REFERENCES "USERS"("ID");
ALTER TABLE "MATCHES" ADD CONSTRAINT "MATCHES_fk1" FOREIGN KEY ("TEAM_HOME") REFERENCES "TEAMS"("ID");

ALTER TABLE "MATCHES" ADD CONSTRAINT "MATCHES_fk2" FOREIGN KEY ("TEAM_AWAY") REFERENCES "TEAMS"("ID");
ALTER TABLE "BETS" ADD CONSTRAINT "BETS_fk1" FOREIGN KEY ("USER_ID") REFERENCES "USERS"("ID");
ALTER TABLE "BETS_MATCHES" ADD CONSTRAINT "BETS_MATCHES_fk0" FOREIGN KEY ("BET_ID") REFERENCES "BETS"("ID");

ALTER TABLE "BETS_MATCHES" ADD CONSTRAINT "BETS_MATCHES_fk1" FOREIGN KEY ("MATCH_ID") REFERENCES "MATCHES"("ID");