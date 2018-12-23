CREATE TABLE nts_maps (
                        name		VARCHAR(6)    PRIMARY KEY,
                        snippet		VARCHAR(40)   NULL,
                        north		DECIMAL(5,2)  NOT NULL,
                        south		DECIMAL(5,2)  NOT NULL,
                        east		DECIMAL(5,2)  NOT NULL,
                        west		DECIMAL(5,2)  NOT NULL,
                        parent          VARCHAR(6)    NULL
);

CREATE INDEX idx_nts_maps_north ON nts_maps (north);
CREATE INDEX idx_nts_maps_south ON nts_maps (south);
CREATE INDEX idx_nts_maps_east ON nts_maps (east);
CREATE INDEX idx_nts_maps_west ON nts_maps (west);
CREATE INDEX idx_nts_maps_parent ON nts_maps (parent);