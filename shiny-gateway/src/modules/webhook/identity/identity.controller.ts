import { Body, Controller, Post } from '@nestjs/common';
import { ApiTags } from '@nestjs/swagger';
import { SyncIdentityDto } from './dto/identity.dto';

@ApiTags('Webhook')
@Controller('webhook')
export class IdentityController {
  @Post('identity')
  async handleIdentityWebhook(@Body() payload: any) {
    console.log(payload.details);
  }
}
