import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Student } from '../student';
import { StudentService } from '../student.service';

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrls: ['./student-details.component.css']
})
export class StudentDetailsComponent implements OnInit {

  rollNo: number=0;
  student : Student= new Student();
  constructor(private route: ActivatedRoute,
    private studentService: StudentService) { }

  ngOnInit(): void {
    this.rollNo = this.route.snapshot.params['rollNo'];

    this.student = new Student();
    this.studentService.getStudentById(this.rollNo).subscribe(data =>{
      this.student = data;
    });




  }

}
