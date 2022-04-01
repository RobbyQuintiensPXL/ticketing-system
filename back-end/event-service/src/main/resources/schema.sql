DROP TABLE IF EXISTS EVENTS;
CREATE TABLE EVENTS (
                      id INT AUTO_INCREMENT  PRIMARY KEY,
                      event_name VARCHAR(50) NOT NULL,
                      event_type ENUM('CONCERT', 'NIGHTLIFE', 'SPORTS', 'GAMING', 'CULTURE')
);