from __future__ import division
from __future__ import print_function
from ortools.sat.python import cp_model
import schedule
import time
import mysql.connector

def main():
    mydb = mysql.connector.connect(
    host="localhost",
    user="root",
    passwd="root",
    database="scheduler"
    )
    mycursor = mydb.cursor()
    mycursor.execute("Delete from final");
    sql = "INSERT INTO final (day,shift,nid,req) VALUES (%s, %s, %s, %s)"

    shift_requests=[]
    n=[]
    s1=[]
    s2=[]
    s3=[]
    s4=[]
    s5=[]
    s6=[]
    s7=[]
    n1=0
    n2=0
    n3=0
    n4=0
    n5=0
    n6=0
    n7=0
    k=0

    def convert(t):
        s=[]
        s.clear()
        if t==0 :
           s.append(0)
           s.append(0)
           s.append(0)
           
        elif t==1:
            s.append(1)
            s.append(0)
            s.append(0)

        elif t==2:
            s.append(0)
            s.append(1)
            s.append(0)

        elif t==3:
            s.append(0)
            s.append(0)
            s.append(1)

        return s
            
    def get(x):
        sql1="Select d1,d2,d3,d4,d5,d6,d7 from shiftreq where id=%s"
        val1=(x)
        mycursor.execute(sql1,val1)
        res = mycursor.fetchall()
        for i in res:
            n1=i[0]
            n2=i[1]
            n3=i[2]
            n4=i[3]
            n5=i[4]
            n6=i[5]
            n7=i[6]
        s1=convert(n1)
        s2=convert(n2)
        s3=convert(n3)
        s4=convert(n4)
        s5=convert(n5)
        s6=convert(n6)
        s7=convert(n7)
        n=[s1,s2,s3,s4,s5,s6,s7]
        shift_requests.append(n)

    mycursor.execute("Select id from nurses")
    myresult = mycursor.fetchall()
    for x in myresult:
        k=k+1
        get(x)
 
    num_nurses = k
    num_shifts = 3
    num_days = 7
    all_nurses = range(num_nurses)
    all_shifts = range(num_shifts)
    all_days = range(num_days)

    for i in shift_requests:
        print(i)
    
    model = cp_model.CpModel()

   
    shifts = {}
    for n in all_nurses:
        for d in all_days:
            for s in all_shifts:
                shifts[(n, d,
                        s)] = model.NewBoolVar('shift_n%id%is%i' % (n, d, s))

   
    for d in all_days:
        for s in all_shifts:
            model.Add(sum(shifts[(n, d, s)] for n in all_nurses) == 1)


    for n in all_nurses:
        for d in all_days:
            model.Add(sum(shifts[(n, d, s)] for s in all_shifts) <= 1)

  
    min_shifts_per_nurse = (num_shifts * num_days) // num_nurses
    max_shifts_per_nurse = min_shifts_per_nurse + 1
    for n in all_nurses:
        num_shifts_worked = sum(
            shifts[(n, d, s)] for d in all_days for s in all_shifts)
        model.Add(min_shifts_per_nurse <= num_shifts_worked)
        model.Add(num_shifts_worked <= max_shifts_per_nurse)

    model.Maximize(
        sum(shift_requests[n][d][s] * shifts[(n, d, s)] for n in all_nurses
            for d in all_days for s in all_shifts))
   
    solver = cp_model.CpSolver()
    solver.Solve(model)
    for d in all_days:
        day=d
        print('Day', d)
        for n in all_nurses:
            for s in all_shifts:
                nurid=0
                shftreq=0
                if solver.Value(shifts[(n, d, s)]) == 1:
                    if shift_requests[n][d][s] == 1:
                        print('Nurse', n, 'works shift', s, '(requested).')
                        shiftid=s
                        shftreq=1
                        nurid=n+1
                        day=d
                    else:
                        print('Nurse', n, 'works shift', s, '(not requested).')
                        shiftid=s
                        shftreq=0
                        nurid=n+1
                        day=d
                    val=(day,shiftid,nurid,shftreq)
                    mycursor.execute(sql, val)

        mydb.commit()
                    
        print()

if __name__ == '__main__':
    main()
