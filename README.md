# Java-Agency-Threds
In this project i created an agency-center and simulated a process of an incoming call to the the service center of the agency and converting it to an operation and excute it.
In the agency-center we have 5 Functionaries that will preform as threads in the project :
1. We have 5 secretaries that receiving calls( a call is also a thread) from an txt file and converting the calls into a task and entering each task into a queue of tasks (vector).
2. Then come a task manager (we have 3 of them) that Pull out of the queue a task and convert it into a strategy and entering it into an information system.
*The information system is a class with an access to the database of the agency
*The database of the agency is a class that connected to MySql. in this class we are creating a table of strategies.
3. Then we have 3 operators that pull out of the information system a strategy and convert it into an operation by assigning the number of agents and vehicles required to each operation and entering the operation into a bounded queue (vector) of operations.
4. Then come an executive (we have 5 of them) and pull out of the bounded queue an operation and execute it (simulated by the function sleep), when he is coming back from an operation he is updating the agency manager.
5. The agency manager knows how many operations his agency should execute each day, when the agency finish the required amount of operations he is ending the day (simulated by killing all the threads).
