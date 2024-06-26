import { Injectable } from '@nestjs/common';

@Injectable()
export class AppService {
  getHello(): string {
    return 'Heads up! This is the Shiny API Gateway!';
  }
}
