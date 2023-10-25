
using Microsoft.Extensions.Configuration;
using NLog;
using sonnette.rasppi.Models;
using System.Device.Gpio;
using System.Device.Pwm;
using System.Net;
using System.Net.Http.Headers;
using System.Net.Http.Json;
namespace sonnette.rasppi;

public class Program {
    static string apiUri;
    static int pinTransistor;
    static int pinButton;
    static int sonnetteId;
    static int pinServoMotor;
    private static readonly NLog.Logger Logger = NLog.LogManager.GetCurrentClassLogger();

    public static void Main(string[] args) {
        Console.WriteLine("Hello, World!");

        // Build a config object, using env vars and JSON providers.
        IConfigurationBuilder builder = new ConfigurationBuilder();

        JsonConfigurationExtensions.AddJsonFile(builder, "appsettings.json");

        IConfiguration config = builder.Build();

        // Get values from the config given their key and their target type.
        apiUri = config.GetRequiredSection("apiUri").Value;
        pinTransistor = int.Parse(config.GetRequiredSection("pinTransistor").Value);
        pinButton = int.Parse(config.GetRequiredSection("pinButton").Value);
        sonnetteId = int.Parse(config.GetRequiredSection("sonnetteId").Value);
        pinServoMotor = int.Parse(config.GetRequiredSection("pinServoMotor").Value);

        GpioController controller = new GpioController(PinNumberingScheme.Board);

        controller.OpenPin(pinTransistor, PinMode.Output);
        controller.OpenPin(pinButton, PinMode.Input);


    //SERVO
    // LOW HELICE MEME SENS BOITIER
    // controller.OpenPin(pinServoMotor, PinMode.Output);
    //controller.OpenPin(pinServoMotor, PinMode.Input);
    //Servo servo = new Servo(Device.CreatePwmPort(Device.Pins.D08), NamedServoConfigs.SG90);
    //Console.WriteLine(controller.Read(pinServoMotor));
    //controller.Write(pinServoMotor, PinValue.High);

    PinValue button;
        PinValue prevButton = false;
        bool isOn = false;
        while (true) {
            button = controller.Read(pinButton);
            if (button == PinValue.Low && prevButton != button) {
                RunAsync(controller, pinTransistor).GetAwaiter().GetResult();
                /*
                isOn = !isOn;
                if (isOn) {
                    controller.Write(pinTransistor, PinValue.High);
                } else {
                    controller.Write(pinTransistor, PinValue.Low);
                }
                */
            }
            prevButton= controller.Read(pinButton);
            Thread.Sleep(100);
        }
    }

    static async Task RunAsync(GpioController controller, int pinValue) {
        HttpClient client = new HttpClient();
        client.BaseAddress = new Uri(apiUri);
        client.DefaultRequestHeaders.Accept.Clear();
        client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

        bool isGood = false;

        try {
            Sonnette sonnette = new Sonnette {
                Id = sonnetteId,
                Date = DateTime.Now,
                TypeAppui = 1
            };

            var statusCode = await CreateProductAsync(sonnette, client);
            switch (statusCode)
            {
                case HttpStatusCode.OK:
                case HttpStatusCode.Created:
                case HttpStatusCode.Accepted:
                case HttpStatusCode.NonAuthoritativeInformation:
                case HttpStatusCode.NoContent:
                case HttpStatusCode.ResetContent:
                    isGood = true;
                    break;

            }
            Console.WriteLine($"Status code returned {statusCode} ({(int)statusCode})");
            Logger.Info($"Status code returned {statusCode} ({(int)statusCode})");
        }
        catch (Exception e)
        {
            Console.WriteLine(e.Message);
            Logger.Error(e);
        }

        if (isGood)
        {
            controller.Write(pinTransistor, PinValue.High);
            Thread.Sleep(1000);
            controller.Write(pinTransistor, PinValue.Low);
        }
        else
        {
            controller.Write(pinTransistor, PinValue.High);
            Thread.Sleep(100);
            controller.Write(pinTransistor, PinValue.Low);
            Thread.Sleep(100);
            controller.Write(pinTransistor, PinValue.High);
            Thread.Sleep(100);
            controller.Write(pinTransistor, PinValue.Low);
            Thread.Sleep(100);
            controller.Write(pinTransistor, PinValue.High);
            Thread.Sleep(100);
            controller.Write(pinTransistor, PinValue.Low);
            Thread.Sleep(100);
            controller.Write(pinTransistor, PinValue.High);
            Thread.Sleep(100);
            controller.Write(pinTransistor, PinValue.Low);
            Thread.Sleep(100);
            controller.Write(pinTransistor, PinValue.High);
            Thread.Sleep(100);
            controller.Write(pinTransistor, PinValue.Low);
            Thread.Sleep(100);
            controller.Write(pinTransistor, PinValue.High);
            Thread.Sleep(100);
            controller.Write(pinTransistor, PinValue.Low);
            Thread.Sleep(100);
        }
    }

    static async Task<HttpStatusCode> CreateProductAsync(Sonnette sonnette, HttpClient client)
    {
        HttpResponseMessage response = await client.PostAsJsonAsync("api/Notification", sonnette);

        return response.StatusCode;
    }
}
