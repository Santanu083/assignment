package Assignment_Sample
import cats.implicits._
trait InterviewerResponse[F[_]] {
def post:F[InterviewerResponse.InterviewResponse]
}
object InterviewerResponse{
  def apply[F[]](implicit ir:InterviewerResponse[F]): InterviewerResponse[F] = ir

  final case class InterviewResponse(interviewResponse:String)extends AnyVal
  object InterviewResponse{
    implicit val interviewResponseDecoder:Decoder[InterviewResponse]=deriveDecoder[InterviewResponse]
    implicit def interviewResponseEntityDecoder[F[_]:Sync]:EntityDecoder[F,InterviewResponse]=jsonOf

    implicit val interviewResponseEncoder:Encoder[InterviewResponse]=deriveEncoder[InterviewResponse]
    implicit def interviewResponseEntityEncoder[F[_]:Applicative]:EntityEncoder[F,InterviewResponse]=jsonEncoderOf
  }

  final case class InterviewResponseError(e:Throwable)extends RuntimeException
 def send[F[_]:Sync](c:Client[F]): InterviewerResponse[F]=new InterviewerResponse[F]{
   val dsl=new Http4sClientDsl[F]{}
   import dsl._
   def post:F[InterviewerResponse.InterviewResponse]={
     c.expect[InterviewResponse](POST(Uri.uri(" http://www.randomtext.me/download/txt/lorem/p-1/10-35/ "),
       UrlForm(
                "content-type"->"Application/json",
                "client"->"Interviewer",
                "client_secret"->"s2d3r5t"

     )
     )).adaptError{case t=>InterviewResponseError(t)}
   }

 }
}

