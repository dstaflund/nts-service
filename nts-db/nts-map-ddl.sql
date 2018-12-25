-- 1.  Run this first
CREATE TABLE nts_maps (
                        name		VARCHAR(6)    PRIMARY KEY,
                        snippet		VARCHAR(40)   NULL,
                        north		DECIMAL(5,2)  NOT NULL,
                        south		DECIMAL(5,2)  NOT NULL,
                        east		DECIMAL(5,2)  NOT NULL,
                        west		DECIMAL(5,2)  NOT NULL,
                        parent          VARCHAR(6)    NULL
);

-- 2.  Then do an import into the database (of nts-map-dml.csv)

-- 3.  Then run the following query
UPDATE nts_maps
   SET parent = CASE
                    WHEN LENGTH(name) < 4 THEN NULL
                    WHEN LENGTH(name) = 4 THEN SUBSTRING(name FROM 1 FOR 3)
                    ELSE SUBSTRING(name FROM 1 FOR 4)
                END;

-- 4.  Then create the following indexes and constraints
CREATE INDEX idx_nts_maps_north ON nts_maps (north);
CREATE INDEX idx_nts_maps_south ON nts_maps (south);
CREATE INDEX idx_nts_maps_east ON nts_maps (east);
CREATE INDEX idx_nts_maps_west ON nts_maps (west);
CREATE INDEX idx_nts_maps_parent ON nts_maps (parent);
ALTER TABLE nts_maps ADD CONSTRAINT fk_nts_maps_parent_name FOREIGN KEY (parent) REFERENCES nts_maps(name);