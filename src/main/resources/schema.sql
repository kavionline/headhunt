DROP TABLE agents IF EXISTS;
DROP TABLE workers IF EXISTS;
DROP TABLE recruitments IF EXISTS;

CREATE TABLE agents (
  id         INTEGER IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR(30),
  address    VARCHAR(255),
  telephone  VARCHAR(20)
);
CREATE INDEX agents_id ON agents (id);

CREATE TABLE workers (
  id         INTEGER IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR(30),
  address    VARCHAR(255),
  telephone  VARCHAR(20),
  speciality VARCHAR(50)
);
CREATE INDEX workers_id ON workers (id);

CREATE TABLE recruitments (
  id          	INTEGER IDENTITY PRIMARY KEY,
  agent_id   INTEGER NOT NULL,
  worker_id   	INTEGER NOT NULL,
  join_date  	DATE
);
ALTER TABLE recruitments ADD CONSTRAINT fk_recruitments_agents FOREIGN KEY (agent_id) REFERENCES agents (id);
ALTER TABLE recruitments ADD CONSTRAINT fk_recruitments_workers FOREIGN KEY (worker_id) REFERENCES workers (id);
CREATE INDEX recruitments_agent_id ON recruitments (agent_id);
CREATE INDEX recruitments_worker_id ON recruitments (worker_id);


