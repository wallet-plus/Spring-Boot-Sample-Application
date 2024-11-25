import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environment/environment';

@Injectable({
  providedIn: 'root',
})
export class StaffService {
  private baseUrl = environment.apiUrl; // Replace with your API endpoint

  constructor(private http: HttpClient) {}

  // Get the list of all staff
  getStaffList(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}users`);
  }

  // Get details of a single staff member by ID
  getStaffById(id: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${id}`);
  }

  // Add a new staff member
  addStaff(staffData: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}`, staffData);
  }

  // Update an existing staff member by ID
  updateStaff(id: number, staffData: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/${id}`, staffData);
  }

  // Delete a staff member by ID
  deleteStaff(id: number): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/${id}`);
  }
}
