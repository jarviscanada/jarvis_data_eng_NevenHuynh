# Introduction
This project contains a compilation of SQL exercises to improve one's skills in writing queries. The database being used is a sample country club and the topics include:
- CRUD (create, read, update, delete)
- Basic filtering using the 'WHERE' clause
- Joining tables 
- Aggregation using aggregate functions
- String manipulation

The exercises can be found on this [website](https://pgexercises.com/).

# SQL Queries
The tables can be set up using the included DDL script 'clubdata.sql' with the command
```
# Modify this query for your database (database name, connection, etc)
psql -U <username> -f clubdata.sql -d postgres -x -q
```
The tables can also be set up using the following SQL script.

###### Table Setup (DDL)
To create the members table:
```
create table cd.members(
  memid integer not null, 
  surname varchar(200) not null, 
  firstname varchar(200) not null, 
  address varchar(300) not null, 
  zipcode integer not null, 
  telephone varchar(20) not null, 
  recommendedby integer, 
  joindate timestamp not null, 
  constraint members_pk primary key (memid), 
  constraint fk_members_recommendedby foreign key (recommendedby) references cd.members(memid) on delete set null
);
```
To create the facilities table:
```
create table cd.facilities(
  facid integer not null, 
  name varchar(100) not null, 
  membercost numeric not null, 
  guestcost numeric not null, 
  initialoutlay numeric not null, 
  monthlymaintenance numeric not null, 
  constraint facilities_pk primary key (facid)
);
```
To create the bookings table:
```
create table cd.bookings(
  bookid integer not null, 
  facid integer not null, 
  memid integer not null, 
  starttime timestamp not null, 
  slots integer not null, 
  constraint bookings_pk primary key (bookid), 
  constraint fk_bookings_facid foreign key (facid) references cd.facilities(facid), 
  constraint fk_bookings_memid foreign key (memid) references cd.members(memid)
);

```
### Modifying Data
###### Question 1: Insert some data into a table
The club is adding a new facility - a spa. We need to add it into the facilities table. Use the following values:
facid: 9, Name: 'Spa', membercost: 20, guestcost: 30, initialoutlay: 100000, monthlymaintenance: 800.
```
INSERT INTO cd.facilities (
  facid, NAME, membercost, guestcost, initialoutlay, monthlymaintenance
) 
VALUES 
  (9, 'Spa', 20, 30, 100000, 800);

```

###### Question 2: Insert calculated data into a table
Let's try adding the spa to the facilities table again. This time, though, we want to automatically generate the value for the next facid, rather than specifying it as a constant. Use the following values for everything else:
Name: 'Spa', membercost: 20, guestcost: 30, initialoutlay: 100000, monthlymaintenance: 800.
```
INSERT INTO cd.facilities(
  facid, name, membercost, guestcost, initialoutlay, monthlymaintenance
)
VALUES
((SELECT max(facid) FROM cd.facilities)+1, 'Spa', 20, 30, 100000, 800);
```

###### Question 3: Update some existing data
We made a mistake when entering the data for the second tennis court. The initial outlay was 10000 rather than 8000: you need to alter the data to fix the error.
```
UPDATE
	cd.facilities
SET 
	initialoutlay=10000
WHERE 
	facid=1;
```

###### Question 4: Update a row based on the contents of another row
We want to alter the price of the second tennis court so that it costs 10% more than the first one. Try to do this without using constant values for the prices, so that we can reuse the statement if we want to.
```
UPDATE 
	cd.facilities
SET
	membercost = (SELECT membercost FROM cd.facilities WHERE facid = 0) * 1.1,
	guestcost = (SELECT guestcost FROM cd.facilities WHERE facid = 0) * 1.1
WHERE 
	facid = 1;
```

###### Question 5: Delete all bookings
As part of a clearout of our database, we want to delete all bookings from the cd.bookings table. How can we accomplish this?
```
DELETE FROM cd.bookings;
```

###### Question 6: Delete a member from the cd.members table
We want to remove member 37, who has never made a booking, from our database. How can we achieve that?
```
DELETE 
FROM 
	cd.members 
WHERE 
	memid = 37;
```
### Basics
###### Question 1: Control which rows are retrieved - part 2
How can you produce a list of facilities that charge a fee to members, and that fee is less than 1/50th of the monthly maintenance cost? Return the facid, facility name, member cost, and monthly maintenance of the facilities in question.
```
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
```

###### Question 2: Basic string search
How can you produce a list of all facilities with the word 'Tennis' in their name?
```
SELECT
	*
FROM
	cd.facilities
WHERE
	name LIKE '%Tennis%';
```

###### Question 3: Matching against multiple possible values
How can you retrieve the details of facilities with ID 1 and 5? Try to do it without using the OR operator.
```
SELECT
	*
FROM
	cd.facilities
WHERE
	facid IN (1, 5);
```

###### Question 4: Working with dates
How can you produce a list of members who joined after the start of September 2012? Return the memid, surname, firstname, and joindate of the members in question.
```
SELECT
	memid, 
  surname, 
  firstname, 
  joindate
FROM
	cd.members
WHERE
	joindate >= '2012-09-01';
```

###### Question 5: Combining results from multiple queries
You, for some reason, want a combined list of all surnames and all facility names
```
SELECT
	surname
FROM
	cd.members
UNION
SELECT
	name
FROM
	cd.facilities;
```

### Join 
###### Question 1: Retrieve the start times of members' bookings
How can you produce a list of the start times for bookings by members named 'David Farrell'?
```
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
```

###### Question 2: Work out the start times of bookings for tennis courts
How can you produce a list of the start times for bookings for tennis courts, for the date '2012-09-21'? Return a list of start time and facility name pairings, ordered by the time.
```
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
```

###### Question 3: Produce a list of all members, along with their recommender
How can you output a list of all members, including the individual who recommended them (if any)? Ensure that results are ordered by (surname, firstname).
```
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
```

###### Question 4: Product a list of all members who have recommended another member
How can you output a list of all members who have recommended another member? Ensure that there are no duplicates in the list, and that results are ordered by (surname, firstname).
```
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
```

###### Question 5: Product a list of all members, along with their recommender without joins
How can you output a list of all members, including the individual who recommended them (if any), without using any joins? Ensure that there are no duplicates in the list, and that each firstname + surname pairing is formatted as a column and ordered.
```
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
```

### Aggregation
###### Question 1: Count the number of recommendations each member makes
Produce a count of the number of recommendations each member has made. Order by member ID.
```
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
```

###### Question 2: List the total slots booked per facility
Produce a list of the total number of slots booked per facility. For now, just produce an output table consisting of facility id and slots, sorted by facility id.
```
SELECT
	facid,
	SUM(slots) AS "Total Slots"
FROM
	cd.bookings
GROUP BY
	facid
ORDER BY
	facid ASC ;
```

###### Question 3: List the total slots booked per facility in a given month
Produce a list of the total number of slots booked per facility in the month of September 2012. Produce an output table consisting of facility id and slots, sorted by the number of slots.
```
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
```

###### Question 4: List the total slots booked per facility per month
Produce a list of the total number of slots booked per facility per month in the year of 2012. Produce an output table consisting of facility id and slots, sorted by the id and month.
```
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
```

###### Question 5: Find the count of members who have made at least one booking
Find the total number of members (including guests) who have made at least one booking.
```
SELECT
	COUNT(DISTINCT memid)
FROM
	cd.bookings;
```

###### Question 6: List each member's first booking after September 1st 2012
Produce a list of each member name, id, and their first booking after September 1st 2012. Order by member ID.
```
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
```

###### Question 7: Produce a list of member names, with each row containing the total member count
Produce a list of member names, with each row containing the total member count. Order by join date, and include guest members.
```
SELECT
	COUNT(*) OVER(),
	firstname,
	surname
FROM
	cd.members
ORDER BY
	joindate;
```

###### Question 8: Produce a numbered list of members
Produce a monotonically increasing numbered list of members (including guests), ordered by their date of joining. Remember that member IDs are not guaranteed to be sequential.
```
SELECT
	ROW_NUMBER() OVER(ORDER BY joindate),
	firstname,
	surname
FROM
	cd.members
ORDER BY
	joindate;
```

###### Question 9: Output the facility id that has the highest number of slots booked, again
Output the facility id that has the highest number of slots booked. Ensure that in the event of a tie, all tieing results get output.
```
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
```

### String 
###### Question 1: Format the names of members
Output the names of all members, formatted as 'Surname, Firstname'
```
SELECT 
	surname || ', ' || firstname AS name
FROM 
	cd.members;
```

###### Question 2: Find telephone numbers with parentheses
You've noticed that the club's member table has telephone numbers with very inconsistent formatting. You'd like to find all the telephone numbers that contain parentheses, returning the member ID and telephone number sorted by member ID.
```
SELECT 
	memid,
	telephone
FROM 
	cd.members
WHERE
	telephone LIKE '(%)%';
```

###### Question 3: Count the number of members whose surname starts with each letter of the alphabet
You'd like to produce a count of how many members you have whose surname starts with each letter of the alphabet. Sort by the letter, and don't worry about printing out a letter if the count is 0.
```
SELECT 
	SUBSTR(surname, 1, 1) AS letter,
	count(*) AS count
FROM 
	cd.members
GROUP BY
	letter
ORDER BY
	letter
```