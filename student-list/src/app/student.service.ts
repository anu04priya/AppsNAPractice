import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from './student';


@Injectable({
  providedIn: 'root'
})
export class StudentService {
  [x: string]: any;
  
  private baseURL = "http://localhost:8080/api/students";

  constructor(private httpClient: HttpClient) { }

  getStudentsList(): Observable<Student[]>{
    return this.httpClient.get<Student[]>(`${this.baseURL}`);
  }

  createStudent(student: Student): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, student);
  }

  getStudentById(rollNo: number): Observable<Student>{
    return this.httpClient.get<Student>(`${this.baseURL}/${rollNo}`);
  }

  updateStudent(rollNo:number, student: Student): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${rollNo}`, student);
  }
  deleteStudent(rollNo: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${rollNo}`);
  }
  }

