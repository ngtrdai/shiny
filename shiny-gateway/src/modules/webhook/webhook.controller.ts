import { Body, Controller, Post } from '@nestjs/common';

@Controller('webhook')
export class WebhookController {
  @Post('identity')
  async handleIdentityWebhook(@Body() payload: any) {
    console.log('Identity Webhook', payload);
  }
}
