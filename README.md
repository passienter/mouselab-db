# MouseLabDB

A database-based solution for storing and sorting lab animal information

<h2>Creators</h2>
Jessie Chen (<a href="mailto:jchen18@luc.edu">jchen18@luc.edu</a>) - Bioinformatics Consultant</br>
Andrew Himebaugh (<a href="mailto:ahimebaugh1@luc.edu">ahimebaugh1@luc.edu</a>) - Interface Engineer</br>
Sam Jaros (<a href="mailto:sjaros@luc.edu">sjaros@luc.edu</a>) - Design and Concept</br>
Kenzo Scheerlinck (<a href="mailto:kscheerlinck@luc.edu">kscheerlinck@luc.edu</a>) - Backend Engineer</br>

<h2>Abstract</h2>
The goal of this project is to create a database for the management and tracking of lab animals, their genotypes, their family history, and other relevant identifiers. Much time and effort is taken to track lab animals of all types as most laboratories simply use a notebook or a shared excel sheet. MouseLab DB aims to allow lab technicians to quickly add, lookup, and track a colony just as they would with legacy options while adding in data validation, user authentication, remote access, and the possibility to integrate with more complex colony-planning algorithms.<br><br>

Users of MouseLab DB should expect to see a great decrease in missing or incomplete data and mistakes due to incorrect information. Because the information is stored in a purpose-built database rather than written on paper or tracked in a general data-processing program like excel, MouseLab DB can prompt users to input missing information, remind users of important dates, and point out a situation which requires human attention. User authentication is a large part of MouseLab DB because nearly all science is a collaborative effort. A self-checking, cloud-hosted database stops possible conflicts of illegible writing or shorthand which may not be comprehended by other lab members.

<h2>Database Design</h2>
Our database will have one table that will contain all of the mice.<br>
We will also use phpMyAdmin to create the table (whether through localhost [xampp] or hosted).<br><br>
<b>Fields:</b><br>
ID (A_I)<br>
Alphanumerical ID (String)<br>
Sex (char)<br>
DOB (Date)<br>
DOD (Date)<br>
Status (int)<br>
Mother (an ID)<br>
Father (an ID)<br>
Strain/Genotype (String)<br>

<h2>Milestones</h2>
Create classes for storing information<br>
Write a text-based interface for creating and interacting with a local database<br>
Create MySQL database<br>
Write a text-based interface for interacting with the database<br>
Create a text-based web interface for interacting with the database<br>
Add user authentication to the web interface<br>
Create a GUI for interacting with the database<br>

<h2>Related Project</h2>
<b><a href="https://github.com/samtools/htsjdk/blob/master/src/main/java/htsjdk/variant/variantcontext/Genotype.java">GenotypeBuilder</a></b> (freely available, open source)<br>
This class and its corresponding “Genotype” and “Allele” classes provide a comprehensive way of storing genetic information. Though these classes have many more methods than we would need, we may use their code structure while writing our own classes.
