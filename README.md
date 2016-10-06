# JMU Parking Data parser

This Kotlin library is primarily for parsing the data on the number of spaces
available in the JMU parking decks,
http://www.jmu.edu/cgi-bin/parking_get_sign_data.cgi, though it probably could
be used for any similar data from Q-Free TCS.

## XML Layout

I haven't found any documentation for the parking data, but the structure seems
to be, for JMU, similar to the following.

* Carpark, Level
    * Appear to have little to no meaning.
        * There is only 1 carpark element, CarPark ID: 1 -- CarparkName: Parking;
        * and 1 level element, LevelID: 3 -- LevelName: Campus PGS.
* Sign
    * Reflect what is currently displayed on the purple signs with data about
    parking around Campus
    * SignId
        * Unique identifier for the sign
        * See the below table for what ID corresponds to which deck
    * Type
        * Seem to be three types
            * VMS: Signs that display whatever text they're told to
            * DigitsOpenFull: Signs that say the number of spaces or "FULL"
            * Digits: Signs with only number
    * TargetArea
        * All I've seen so far is "Regular"
    * State
        * Online & Communication error
    * Display
        * the current text on the sign
    * Last updated
        * ISO 8601 time stamp of the last time the text was updated


## Sign IDs

As I find more signs, they'll be added to this table

| Deck          | ID |
| --------------|----|
| Mason St.     | 24 |
| Champions Dr. | 2  |
| Warsaw Ave.   | 12 |
