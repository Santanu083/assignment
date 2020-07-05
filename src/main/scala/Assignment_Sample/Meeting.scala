package Assignment_Sample
import cats.Applicative
import cats.implicits._
import io.circe.{Encoder, Json}
import org.http4s.EntityEncoder
import org.http4s.circe._
import java.beans.Encoder

trait Meeting [F[_]]{
  def interview (n:Meeting.Interviewer):F[Meeting.Request_Id]

}
object Meeting{
  implicit def apply[F[_]](implicit evnt:Meeting[F]): Meeting[F] = evnt
  final case class Interviewer(name:String) extends AnyVal
  final case class Request_Id(request: String) extends AnyVal
  object Request_Id{
    implicit val requestEncoder:Encoder[Request_Id]=new Encoder[Request_Id]{
      final def apply(r:Request_Id): Json=Json.obj(
        ("message",Json.fromString(r:request)),
      )
    }
    implicit def requestEntryEncoder[F[_]:Applicative]:EntryEncoder[F,Request_Id]=
      jsonEncoderOf[F,Request_Id]
  }
  def imp[F[_]: Applicative]:Meeting[F]=new Meeting[F]{
    def interview(n:Meeting.Interviewer):F[Meeting.Request_Id]=Request_Id("Interviewer," + n.name).pure[F]
  }
}
