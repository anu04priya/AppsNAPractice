import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Student } from '../student';
import { StudentService } from '../student.service';

@Component({
  selector: 'app-update-student',
  templateUrl: './update-student.component.html',
  styleUrls: ['./update-student.component.css']
})
export class UpdateStudentComponent implements OnInit {
  
  rollNo: number=0;
  student: Student = new Student();
  constructor(private studentService: StudentService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
    this.rollNo = this.route.snapshot.params['rollNo'];

    this.studentService.getStudentById(this.rollNo).subscribe(data =>{
      this.student = data;
    },error => console.log(error));
    
  }

  onSubmit(){
    this.studentService.updateStudent(this.rollNo, this.student).subscribe(data =>{
      this.goToStudentList();

    },error => console.log(error));
  }

  goToStudentList(){
    this.router.navigate(['/students']);
  }
  

}
