-- Modifying data

-- 1. Insert some data into the facilities table
INSERT INTO cd.facilities (
  facid, name, membercost, guestcost, initialoutlay, monthlymaintenance
)
VALUES
  (9, 'Spa', 20, 30, 100000, 800);

-- 2. Insert some more data but using a query to get the id of the previous row
INSERT INTO cd.facilities(
  facid, name, membercost, guestcost, initialoutlay, monthlymaintenance
)
VALUES
  ((SELECT max(facid) FROM cd.facilities)+1, 'Spa', 20, 30, 100000, 800);

-- 3. Update row in the facilities table
UPDATE
	cd.facilities
SET
	initialoutlay=10000
WHERE
	facid=1;

-- 4. Update row in the facilities table using subqueries to get the prices of the previous row
UPDATE
	cd.facilities
set
	membercost = (SELECT membercost FROM cd.facilities WHERE facid = 0) * 1.1,
	guestcost = (SELECT guestcost FROM cd.facilities WHERE facid = 0) * 1.1
WHERE
	facid = 1;

-- 5. Delete all rows from the bookings table
DELETE FROM cd.bookings;

-- 6. Delete a row from the members table
DELETE
FROM
	cd.members
WHERE
	memid = 37;

-- Basics

-- 1. Search for row based on column values
SELECT
	facid,
	name,
	membercost,
	monthlymaintenance
FROM
	cd.facilities
WHERE
	membercost < (monthlymaintenance / 50 )
	AND membercost > 0;

-- 2. Search for rows in facilities that have 'Tennis' in their name
SELECT
	*
FROM
	cd.facilities
WHERE
	name LIKE '%Tennis%';

-- 3. Match against multiple possible values for facid (1, 5)
SELECT
	*
FROM
	cd.facilities
WHERE
	facid IN (1, 5);

-- 4. Match rows where the joindate timestamp is after 2012-09-01
SELECT
	memid,
	surname,
	firstname,
	joindate
FROM
	cd.members
WHERE
	joindate >= '2012-09-01';

-- 5. Create a union between the cd.members surnames and the cd.facilities
SELECT
	surname
FROM
	cd.members
UNION
SELECT
	name
FROM
	cd.facilities;

-- Join

-- 1. Join the cd.members and cd.bookings tables to find the start times for bookings by 'David Farrell'
SELECT
	starttime
FROM
	cd.bookings
INNER JOIN cd.members
	ON
	cd.members.memid = cd.bookings.memid
WHERE
	firstname = 'David'
	AND surname = 'Farrell';

-- 2. Join the cd.bookings and cd.facilities tables to find the start times for bookings at the tennis courts
SELECT
	cd.bookings.starttime AS start,
	cd.facilities.name AS name
FROM
	cd.bookings
INNER JOIN cd.facilities
	ON
	cd.bookings.facid = cd.facilities.facid
WHERE
	starttime BETWEEN '2012-09-21' AND '2012-09-22'
	AND name LIKE 'Tennis Court %'
ORDER BY
	starttime ASC

-- 3. Join the cd.members table with itself to match the recommendedby column with the memid column
SELECT
	mem.firstname AS memfname,
	mem.surname AS memsname,
	rec.firstname AS recfname,
	rec.surname AS recsname
FROM
	cd.members mem
LEFT JOIN cd.members rec
	ON
	mem.recommendedby = rec.memid
ORDER BY
	memsname,
	memfname ASC;

-- 4. Join the cd.members table with itself to match the recommendedby column with the memid column
SELECT
	DISTINCT rec.firstname AS firstname,
	rec.surname AS surname
FROM
	cd.members rec
INNER JOIN
	cd.members mem
	ON
	mem.recommendedby = rec.memid
ORDER BY
	surname,
	firstname ASC;

-- 5. Use a subquery to get the names of members who recommended another member by matching the recommendedby column with the memid column
SELECT
	DISTINCT mem.firstname || ' ' || mem.surname AS member,
	(
	SELECT
		rec.firstname || ' ' || rec.surname AS recommender
	FROM
		cd.members rec
	WHERE
		rec.memid = mem.recommendedby)
FROM
	cd.members mem
ORDER BY
	member;

-- Aggregation

-- 1. Count the number of rows the same memid appears for the recommendedby column, grouped by recommendedby
SELECT
	recommendedby,
	count(*)
FROM
	cd.members
WHERE
	recommendedby IS NOT NULL
GROUP BY
	recommendedby
ORDER BY
	recommendedby ASC;

-- 2. Sum the rows in the slots column grouped by facid
SELECT
	facid,
	SUM(slots) AS "Total Slots"
FROM
	cd.bookings
GROUP BY
	facid
ORDER BY
	facid ASC ;

-- 3. Sum the rows in the slots column in the month of September 2012 grouped by facid
SELECT
	facid,
	SUM(slots) AS "Total Slots"
FROM
	cd.bookings
WHERE
	starttime BETWEEN '2012-09-01' AND '2012-10-01'
GROUP BY
	facid
ORDER BY
	"Total Slots" ASC

-- 4. Sum the rows in the slots column for each month in 2012 grouped by facid
SELECT
	facid,
	EXTRACT(MONTH FROM starttime) AS month,
	SUM(slots) AS "Total Slots"
FROM
	cd.bookings
WHERE
	EXTRACT(YEAR FROM starttime) = 2012
GROUP BY
	facid,
	month
ORDER BY
	facid,
	month

-- 5. Count the distinct rows in memid from cd.bookings
SELECT
	COUNT(DISTINCT memid)
FROM
	cd.bookings;

-- 6. Get the minimum timestamp in the starttime column for each member's booking after September 1st, 2012
SELECT
	DISTINCT mem.surname,
	mem.firstname,
	mem.memid,
	MIN(bk.starttime) AS starttime
FROM
	cd.members mem
INNER JOIN cd.bookings bk
	ON
	mem.memid = bk.memid
WHERE
	bk.starttime >= '2012-09-01'
GROUP BY
	mem.surname,
	mem.firstname,
	mem.memid
ORDER BY
	mem.memid;

-- 7. Get the count of all members and the list of members sorted by joindate
SELECT
	COUNT(*) OVER(),
	firstname,
	surname
FROM
	cd.members
ORDER BY
	joindate;

-- 8. Get the list of members sorted by joindate and include the row number for each row
SELECT
	ROW_NUMBER() OVER(ORDER BY joindate),
	firstname,
	surname
FROM
	cd.members
ORDER BY
	joindate;

-- 9. Finds the facid and the total number of slots for the facility with the highest total bookings
SELECT
	facid,
	total
FROM
	(
	SELECT
		facid,
		sum(slots) total,
		RANK() OVER (ORDER BY sum(slots) DESC) RANK
	FROM
		cd.bookings
	GROUP BY
		facid
	) AS ranked
WHERE
	RANK = 1

-- Strings

-- 1. Get the surnames and firstnames of all members and concatenate them with a comma
SELECT
	surname || ', ' || firstname AS name
FROM
	cd.members;

-- 2. Get the rows of the memid and telephone columns where telephone numbers contain parentheses
SELECT
	memid,
	telephone
FROM
	cd.members
WHERE
	telephone LIKE '(%)%';

-- 3. Obtain the first letter of each surname and count the amount of surnames beginning with each letter, grouped by letter
SELECT
	SUBSTR(surname, 1, 1) AS letter,
	count(*) AS count
FROM
	cd.members
GROUP BY
	letter
ORDER BY
	letter
