CREATE TABLE "voter" (
                                "id" serial NOT NULL,
                                "ex_identifier" bigint NOT NULL UNIQUE,
                                "name" character varying(100) NOT NULL,
                                "candidate_id" bigint,
                                CONSTRAINT "voter_pk" PRIMARY KEY ("id")
);

CREATE TABLE "candidate" (
                                    "id" serial NOT NULL,
                                    "ex_identifier" bigint NOT NULL UNIQUE,
                                    "name" character varying(100) NOT NULL,
                                    CONSTRAINT "candidate_pk" PRIMARY KEY ("id")
);

ALTER TABLE "voter" ADD CONSTRAINT "voter_fk0" FOREIGN KEY ("candidate_id") REFERENCES "candidate"("id");
